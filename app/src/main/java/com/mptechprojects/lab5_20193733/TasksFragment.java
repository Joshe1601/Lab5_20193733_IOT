package com.mptechprojects.lab5_20193733;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mptechprojects.lab5_20193733.adapters.TasksAdapter;
import com.mptechprojects.lab5_20193733.databinding.FragmentTasksBinding;
import com.mptechprojects.lab5_20193733.repository.TasksRepository;
import com.mptechprojects.lab5_20193733.viewmodels.TasksViewModel;

import java.util.ArrayList;


public class TasksFragment extends Fragment {

    String channelId = "channelDefaultPri";
    FragmentTasksBinding binding;
    private TasksViewModel tasksViewModel;
    private TasksAdapter tasksAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);

        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        tasksViewModel.getUserCode().observe(getViewLifecycleOwner(), userCode -> {
            tasksViewModel.fetchTasksForUser(userCode);
        });

        tasksViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            if (tasks != null && !tasks.isEmpty()) {
                tasksAdapter.setTasksList(tasks);
                binding.recyclerViewTasks.setVisibility(View.VISIBLE);
                binding.textViewEmptyTasks.setVisibility(View.GONE);
            } else {
                binding.recyclerViewTasks.setVisibility(View.GONE);
                binding.textViewEmptyTasks.setVisibility(View.VISIBLE);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Canal notificaciones default", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Canal para notificaciones con prioridad default");
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            askPermission();
        }
    }


    private void askPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(getContext(), POST_NOTIFICATIONS) ==
                        PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{POST_NOTIFICATIONS},
                    101);
        }
    }

    private void launchNotification() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent. getActivity(getContext(), 0, intent,
                PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), channelId)
                .setSmallIcon(R.drawable.baseline_add_task_24)
                .setContentTitle("Usuario Guardado!")
                .setContentText("Se ha guardado exitosamente :D" )
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat. from(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            notificationManager.notify(1, builder.build());
        }

    }
}