package com.miramicodigo.notificaciones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.support.v4.app.NotificationCompat
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.view.View
import android.app.NotificationManager
import android.app.NotificationChannel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.R.attr.banner
import android.app.Notification
import android.media.RingtoneManager

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private var mNotificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_channel_01",
                    "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT)
            mNotificationManager!!.createNotificationChannel(channel)
        }
        btnNotificacionSimple.setOnClickListener(this)
        btnNotificacionGrande.setOnClickListener(this)
        btnNotificacionProgreso.setOnClickListener(this)
        btnNotificacionAcciones.setOnClickListener(this)
        btnNotificacionImagenGrande.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNotificacionSimple -> createSimpleNotification(this)
            R.id.btnNotificacionGrande -> createExpandableNotification(this)
            R.id.btnNotificacionImagenGrande -> createBigImageNotification(this)
            R.id.btnNotificacionProgreso -> createProgressNotification(this)
            R.id.btnNotificacionAcciones -> createButtonNotification(this)
        }
    }

    fun createSimpleNotification(context: Context) {
        val notification = NotificationCompat.Builder(context)

        notification.setSmallIcon(R.drawable.ic_android_black_24dp)

        val intent = Intent(context, ResultadoActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val v = longArrayOf(0, 400, 100, 300, 200, 300)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        notification.setContentIntent(pendingIntent)
        notification.setLargeIcon(BitmapFactory.decodeResource(
                context.resources, R.drawable.ic_android_black_24dp))
        notification.setContentTitle("Titulo Notificacion")
        notification.setContentText("Aca colocamos en contenido de la notificacion")
        notification.setSubText("Contenido Subtitulo notificacion")

        mNotificationManager!!.notify(1, notification.build())
    }

    fun createExpandableNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val inboxStyle = NotificationCompat.InboxStyle()
            val lorem = context.resources.getString(R.string.long_lorem)
            val content = lorem.split("\\.".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

            inboxStyle.setBigContentTitle("Titulo Notificacion")
            for (line in content) {
                inboxStyle.addLine(line)
            }

            val notification = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentTitle("Expandable Notificacion")
                    .setContentText("Esto es el contendio de mi notificacion")
                    .setStyle(inboxStyle)
            mNotificationManager!!.notify(2, notification.build())
        } else {

        }
    }

    fun createBigImageNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val notification = NotificationCompat.Builder(context, "")
            notification.setContentTitle("Titulo Big Image Notification")
            notification.setContentText("Contenido notificacion")

            val bigPictureStyle = NotificationCompat.BigPictureStyle()
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(
                    resources, R.drawable.banner))

            notification.setStyle(bigPictureStyle)
            notification.setSmallIcon(R.drawable.ic_android_black_24dp)

            mNotificationManager!!.notify(3, notification.build())

        } else {
            createSimpleNotification(this)
        }
    }

    fun createProgressNotification(context: Context) {
        val progresID = Random().nextInt(1000)

        val notification = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Titulo Notificacion")
                .setContentText("Contenido Notificacion")
                .setTicker("Notificacion de progreso creada")
                .setUsesChronometer(true)
                .setProgress(100, 0, true)

        val downloadTask = object : AsyncTask<Int, Int, Int>() {
            override fun onPreExecute() {
                super.onPreExecute()
                mNotificationManager!!.notify(progresID, notification.build())
            }

            override fun doInBackground(vararg p0: Int?): Int? {
                try {
                    Thread.sleep(5000)
                    var i = 0
                    while (i < 101) {
                        notification
                                .setContentTitle("En progreso...")
                                .setContentText("Se esta ejecutando...")
                                .setProgress(100, i, false)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentInfo(i.toString() + "%")
                        mNotificationManager!!.notify(progresID, notification.build())
                        Thread.sleep(500)
                        i += 5
                    }

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                return null
            }

            override fun onPostExecute(integer: Int?) {
                super.onPostExecute(integer)
                notification
                        .setContentTitle("Progreso terminado")
                        .setContentText("El progreso termino.")
                        .setTicker("Termino el progreso.")
                        .setSmallIcon(R.drawable.ic_android_black_24dp)
                        .setUsesChronometer(false)
                mNotificationManager!!.notify(progresID, notification.build())
            }
        }
        downloadTask.execute()
    }


    fun createButtonNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val intent = Intent(context, ResultadoActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val notification = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentTitle("Titulo notificacion con botones")
                    .setContentText("Aca abajo estan los botones")
                    .addAction(R.drawable.ic_android_black_24dp, "Aceptar", pendingIntent)
                    .addAction(R.mipmap.ic_launcher, "Cancelar", pendingIntent)

            sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

            mNotificationManager!!.notify(1000, notification.build())
        } else {
            Toast.makeText(context, "Necesitas una version mas alta", Toast.LENGTH_LONG).show()
        }
    }
}
