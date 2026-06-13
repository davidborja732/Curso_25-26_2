import 'package:flutter/material.dart';
import 'package:app_gestion_padel/services/user_session.dart';
import 'package:app_gestion_padel/services/api_socios_service.dart';
import 'package:app_gestion_padel/screens/socios/editar_perfil_screen.dart';

class PerfilScreen extends StatefulWidget {
  const PerfilScreen({Key? key}) : super(key: key);

  @override
  State<PerfilScreen> createState() => _PerfilScreenState();
}

class _PerfilScreenState extends State<PerfilScreen> {
  int _imageVersion = 0;

  void _navigateToEditScreen() async {
    final result = await Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const EditarPerfilScreen()),
    );

    if (result == true) {
      setState(() {
        _imageVersion++;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isWide = MediaQuery.of(context).size.width > 600;

    final user = UserSession().loggedInUser;

    final nombre = user?.nombreCompleto ?? 'No disponible';
    final email = user?.email ?? 'No disponible';
    final dni = user?.dni ?? 'No disponible';
    final telefono = user?.telefono ?? 'No disponible';
    final direccion = user?.direccion ?? 'No disponible';
    final cuentaBancaria = user?.cuentaBancaria ?? 'No disponible';
    final String estado = (user?.desactivado == 1) ? 'Desactivada' : 'Activa';

    return Center(
      child: SingleChildScrollView(
        padding: const EdgeInsets.all(24.0),
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 800),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Text(
                'Perfil de usuario',
                textAlign: TextAlign.center,
                style: theme.textTheme.displayMedium?.copyWith(fontSize: 28),
              ),
              const SizedBox(height: 32),
              if (isWide)
                Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _buildAvatar(theme),
                    const SizedBox(width: 32),
                    Expanded(
                      child: Column(
                        children: [
                          _buildReadOnlyField('Nombre completo', nombre, theme),
                          const SizedBox(height: 16),
                          _buildReadOnlyField('DNI', dni, theme),
                        ],
                      ),
                    ),
                  ],
                )
              else
                Column(
                  children: [
                    _buildAvatar(theme),
                    const SizedBox(height: 24),
                    _buildReadOnlyField('Nombre completo', nombre, theme),
                    const SizedBox(height: 16),
                    _buildReadOnlyField('DNI', dni, theme),
                  ],
                ),
              const SizedBox(height: 16),
              _buildReadOnlyField('Correo electrónico', email, theme),
              const SizedBox(height: 16),
              _buildReadOnlyField('Teléfono', telefono, theme),
              const SizedBox(height: 16),
              _buildReadOnlyField('Dirección', direccion, theme),
              const SizedBox(height: 16),
              _buildReadOnlyField(
                'Cuenta bancaria predeterminada',
                cuentaBancaria,
                theme,
              ),
              const SizedBox(height: 16),
              _buildReadOnlyField('Estado de cuenta', estado, theme),
              const SizedBox(height: 48),
              Center(
                child: OutlinedButton(
                  onPressed: _navigateToEditScreen,
                  child: const Text('Editar perfil'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  // 🔥 AVATAR CORREGIDO
  Widget _buildAvatar(ThemeData theme) {
    final user = UserSession().loggedInUser;

    final hasImage =
        user?.imagenPerfil != null &&
        user!.imagenPerfil!.isNotEmpty &&
        user.dni != null;

    ImageProvider? avatarImage;

    if (hasImage) {
      avatarImage = NetworkImage(
        '${ApiSociosService().getProfileImageUrl(user.dni!)}?v=$_imageVersion',
      );
    }

    return CircleAvatar(
      radius: 60,
      backgroundColor: theme.colorScheme.secondary,
      backgroundImage: avatarImage,
      child: avatarImage == null
          ? Icon(
              Icons.person_outline,
              size: 70,
              color: theme.colorScheme.onSecondary,
            )
          : null,
    );
  }

  Widget _buildReadOnlyField(String label, String text, ThemeData theme) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: const EdgeInsets.only(left: 4, bottom: 6),
          child: Text(
            label,
            style: TextStyle(
              fontSize: 13,
              fontWeight: FontWeight.bold,
              color: theme.colorScheme.onSurface.withOpacity(0.7),
            ),
          ),
        ),
        Container(
          width: double.infinity,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
          decoration: BoxDecoration(
            color: const Color(0xFFEAEFBD),
            border: Border.all(
              color: theme.colorScheme.primary.withOpacity(0.5),
            ),
          ),
          child: Text(
            text,
            style: theme.textTheme.bodyLarge?.copyWith(
              fontWeight: FontWeight.w600,
            ),
          ),
        ),
      ],
    );
  }
}
