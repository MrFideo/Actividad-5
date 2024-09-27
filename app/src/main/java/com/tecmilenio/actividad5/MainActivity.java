package com.tecmilenio.actividad5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button btnCalcular;
    private TextView tvEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.date_picker);
        btnCalcular = findViewById(R.id.btn_calcular);
        tvEdad = findViewById(R.id.tv_edad);

        btnCalcular.setOnClickListener(v -> {
            int dia = datePicker.getDayOfMonth();
            int mes = datePicker.getMonth();
            int año = datePicker.getYear();

            Calendar hoy = Calendar.getInstance();
            int añoActual = hoy.get(Calendar.YEAR);
            int mesActual = hoy.get(Calendar.MONTH);
            int diaActual = hoy.get(Calendar.DAY_OF_MONTH);

            int edad = añoActual - año;
            if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
                edad--;
            }

            int hora = hoy.get(Calendar.HOUR_OF_DAY);
            int minuto = hoy.get(Calendar.MINUTE);

            if (año > añoActual || (año == añoActual && mes > mesActual) || (año == añoActual && mes == mesActual && dia > diaActual)) {
                String textoError = "¡Es imposible viajar al futuro... aún!";
                tvEdad.setText(textoError);
            } else {
                // Calcular la edad en años, meses y días
                int edadAños = añoActual - año;
                int edadMeses = mesActual - mes;
                int edadDias = diaActual - dia;

                if (edadDias < 0) {
                    edadMeses--;
                    Calendar prevMonth = Calendar.getInstance();
                    prevMonth.set(añoActual, mesActual - 1, 1);
                    edadDias += prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
                } else if (edadMeses < 0) {
                    edadAños--;
                    edadMeses += 12;
                }

                String textoFinal = "¡Tienes " + edadAños + " años, " + edadMeses + " meses y " + edadDias + " días!\n" +
                        "Hora de consulta: " + hora + ":" + (minuto < 10 ? "0" + minuto : minuto);
                tvEdad.setText(textoFinal);
            }

            // Animación de aparición (busque en internet maneras de hacer una interfaz mas bonita y esta opcion me agrado bastante)
            tvEdad.setAlpha(0f);
            tvEdad.animate().alpha(1f).setDuration(1500);

            Toast.makeText(this, "Edad calculada exitosamente", Toast.LENGTH_SHORT).show();
        });

    }
}
