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

public class DistanceFragment extends Fragment {

    private RadioGroup unitSelector;
    private RadioButton kmToMilesBtn;
    private EditText distanceInput;
    private Button computeBtn;
    private TextView resultDisplay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_dist_conv, container, false);

        unitSelector = layoutView.findViewById(R.id.dist_type_selector);
        kmToMilesBtn = layoutView.findViewById(R.id.choice_km_to_miles);
        distanceInput = layoutView.findViewById(R.id.field_dist_input);
        computeBtn = layoutView.findViewById(R.id.btn_compute_dist);
        resultDisplay = layoutView.findViewById(R.id.txt_dist_res);

        computeBtn.setOnClickListener(v -> calculate());

        return layoutView;
    }

    private void calculate() {
        String inputStr = distanceInput.getText().toString().trim();
        if (TextUtils.isEmpty(inputStr)) {
            Toast.makeText(getActivity(), "Entrée vide", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double value = Double.parseDouble(inputStr);
            double output;

            if (kmToMilesBtn.isChecked()) {
                // Km to Miles
                output = value * 0.621371;
            } else {
                // Miles to Km
                output = value / 0.621371;
            }

            resultDisplay.setText(String.format(Locale.getDefault(), "Résultat : %.2f", output));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Valeur numérique invalide", Toast.LENGTH_SHORT).show();
        }
    }
}