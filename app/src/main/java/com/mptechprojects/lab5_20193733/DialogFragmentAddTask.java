package com.mptechprojects.lab5_20193733;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.mptechprojects.lab5_20193733.databinding.FragmentDialogAddTaskBinding;

public class DialogFragmentAddTask extends DialogFragment {

    FragmentDialogAddTaskBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Lab5_20193733);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDialogAddTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputLayout taskName = view.findViewById(R.id.textFieldTaskTitle);
        TextInputLayout taskDescription = view.findViewById(R.id.textFieldTaskDescription);
        Button save = view.findViewById(R.id.iconButtonSaveTask);
        Button cancel = view.findViewById(R.id.iconButtonCloseDialog);

        cancel.setOnClickListener(v -> dismiss());

    }

}
