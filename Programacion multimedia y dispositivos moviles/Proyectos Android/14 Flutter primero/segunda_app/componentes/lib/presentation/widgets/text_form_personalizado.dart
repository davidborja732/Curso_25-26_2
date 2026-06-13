import 'package:flutter/material.dart';

class TextFormPersonalizado extends StatelessWidget {

  final String? hintText;
  final String? labelText;
  final String? helperText;
  final IconData? icon;
  final IconData? suffixIcon;
  final TextInputType? tipoteclado;
  final bool obscureText;

  final String formPropiedad;
  final Map<String,String> formValues;

  const TextFormPersonalizado({
    super.key, 
    this.hintText, 
    this.labelText, 
    this.helperText, 
    this.icon, 
    this.suffixIcon,
    this.tipoteclado,
    this.obscureText=false, 
    required this.formPropiedad, 
    required this.formValues,
  });

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      //initialValue: 'David.',
      autofocus: true,
      textCapitalization: TextCapitalization.words,
      keyboardType: tipoteclado,
      obscureText: obscureText,
      onChanged: ( value ){
        formValues[formPropiedad]=value;
      },
      validator: (value) {
        
          return value!.length < 5 ? 'Mínimo 5 caracteres' : null;
        
      },
      autovalidateMode: AutovalidateMode.onUserInteraction,
      decoration: InputDecoration(
        border: OutlineInputBorder(),
        hintText: hintText,
        labelText: labelText,
        helperText: helperText,
        //counterText: '3 caracteres',
        suffixIcon: suffixIcon != null ? Icon(suffixIcon) : null,
        //prefixIcon: Icon(Icons.verified_user_outlined)
        icon: icon != null ? Icon(icon) : null ,
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        )
      ),
    );
  }
}