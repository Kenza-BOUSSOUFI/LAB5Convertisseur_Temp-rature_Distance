package com.example.convertisseur_temprature__distance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TemperatureFragment extends Fragment {

    private RadioGroup selectionGroup;
    private RadioButton cToFRadio;
    private EditText inputField;
    private Button actionButton;
    private TextView resultLabel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_temp_conv, container, false);

        selectionGroup = root.findViewById(R.id.temp_mode_group);
        cToFRadio = root.findViewById(R.id.radio_c_to_f);
        inputField = root.findViewById(R.id.input_temp_value);
        actionButton = root.findViewById(R.id.btn_trigger_temp_conv);
        resultLabel = root.findViewById(R.id.label_temp_output);

        actionButton.setOnClickListener(v -> performConversion());

        return root;
    }

    private void performConversion() {
        String rawValue = inputField.getText().toString().trim();
        if (TextUtils.isEmpty(rawValue)) {
            Toast.makeText(getContext(), "Entrez un nombre valide svp", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double sourceVal = Double.parseDouble(rawValue);
            double convertedVal;

            if (cToFRadio.isChecked()) {
                // Celsius to Fahrenheit: (C * 9/5) + 32
                convertedVal = (sourceVal * 1.8) + 32;
            } else {
                // Fahrenheit to Celsius: (F - 32) * 5/9
                convertedVal = (sourceVal - 32) / 1.8;
            }

            resultLabel.setText(String.format(Locale.getDefault(), "Résultat : %.2f", convertedVal));
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Format invalide", Toast.LENGTH_SHORT).show();
        }
    }
}