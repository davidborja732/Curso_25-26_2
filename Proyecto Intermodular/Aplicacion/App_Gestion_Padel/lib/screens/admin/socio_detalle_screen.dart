import 'package:app_gestion_padel/model/socio.dart';
import 'package:app_gestion_padel/screens/admin/add_edit_socio.dart';
import 'package:app_gestion_padel/theme/app_theme.dart';
import 'package:flutter/material.dart';

class SocioDetalleScreen extends StatelessWidget {
  final Socio socio;

  const SocioDetalleScreen({super.key, required this.socio});

  @override
  Widget build(BuildContext context) {
    final isInactive = (socio.desactivado ?? 0) != 0;

    return Scaffold(
      appBar: AppBar(
        title: const Text('DETALLE DE SOCIO'),
        actions: [
          if (!isInactive)
            IconButton(
              icon: const Icon(Icons.edit_outlined),
              tooltip: 'Editar',
              onPressed: () async {
                final nav = Navigator.of(context);
                final saved = await Navigator.push<bool>(
                  context,
                  MaterialPageRoute(
                    builder: (_) => AddEditSocio(socio: socio),
                  ),
                );
                if (saved == true && context.mounted) nav.pop(true);
              },
            ),
        ],
      ),
      body: Center(
        child: ConstrainedBox(
          constraints: const BoxConstraints(maxWidth: 800),
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                _buildSection(
                  context,
                  title: 'Información personal',
                  icon: Icons.person_outline,
                  children: [
                    _buildRow('Nombre completo', socio.nombreCompleto ?? 'N/A'),
                    _buildRow('DNI', socio.dni ?? 'N/A'),
                  ],
                ),
                const SizedBox(height: 20),
                _buildSection(
                  context,
                  title: 'Contacto',
                  icon: Icons.contact_mail_outlined,
                  children: [
                    _buildRow('Email', socio.email ?? 'N/A'),
                    _buildRow('Teléfono', socio.telefono ?? 'N/A'),
                    _buildRow('Dirección', socio.direccion ?? 'N/A'),
                  ],
                ),
                const SizedBox(height: 20),
                _buildSection(
                  context,
                  title: 'Cuenta',
                  icon: Icons.account_balance_outlined,
                  children: [
                    _buildRow(
                      'Cuenta bancaria',
                      socio.cuentaBancaria?.isNotEmpty == true
                          ? socio.cuentaBancaria!
                          : 'N/A',
                    ),
                    _buildRow(
                      'Estado',
                      isInactive ? 'Inactivo' : 'Activo',
                      valueColor: isInactive ? kOrange : kGreen,
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildSection(
    BuildContext context, {
    required String title,
    required IconData icon,
    required List<Widget> children,
  }) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border.all(color: kLightGreen),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(icon, color: kGreen, size: 20),
              const SizedBox(width: 8),
              Text(
                title.toUpperCase(),
                style: Theme.of(context).textTheme.titleMedium?.copyWith(
                  color: kGreen,
                  letterSpacing: 1.2,
                ),
              ),
            ],
          ),
          const Divider(height: 24),
          ...children,
        ],
      ),
    );
  }

  Widget _buildRow(String label, String value, {Color? valueColor}) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 8),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            label,
            style: const TextStyle(
              fontWeight: FontWeight.w600,
              color: Color(0xFF777750),
            ),
          ),
          Text(
            value,
            style: TextStyle(
              fontWeight: FontWeight.bold,
              color: valueColor ?? kOlive,
            ),
          ),
        ],
      ),
    );
  }
}
