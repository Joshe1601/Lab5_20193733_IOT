package com.mptechprojects.lab5_20193733;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mptechprojects.lab5_20193733.databinding.ActivityTasksBinding;

public class TasksActivity extends AppCompatActivity {
    String channelId = "channelDefaultPri";
    ActivityTasksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel();


        binding.extendedFabAddTask.setOnClickListener(v -> {
            DialogFragmentAddTask dialogFragmentAddTask = new DialogFragmentAddTask();
            dialogFragmentAddTask.show(getSupportFragmentManager(), "DialogFragmentAddTask");
        });
    }
    public void createNotificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Canal notificaciones default", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("Canal para notificaciones con prioridad default");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);

            askPermission();
        }
    }

    private void askPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) ==
                        PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(TasksActivity.this,
                    new String[]{POST_NOTIFICATIONS},
                    101);
        }
    }

    private void launchNotification() {
        Intent intent = new Intent(this, TasksActivity.class);
        PendingIntent pendingIntent = PendingIntent. getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_add_task_24)
                .setContentTitle("Usuario Guardado!")
                .setContentText("Se ha guardado exitosamente :D" )
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat. from(this);
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            notificationManager.notify(1, builder.build());
        }

    }
}