package me.dio.copa.catar.notification.scheduler.extensions

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import me.dio.copa.catar.domain.model.Match
import java.time.Duration
import java.time.LocalDateTime


private const val NOTIFICATION_TITLE_KEY = "NOTIFICATION_TITLE_KEY"
private const val NOTIFICATION_CONTENT_KEY = "NOTIFICATION_CONTENT_KEY"

class NotificationMatchesWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val title = inputData.getString(NOTIFICATION_TITLE_KEY)
            ?: throw IllegalArgumentException("Title is required")
        val content = inputData.getString(NOTIFICATION_CONTENT_KEY)
            ?: throw IllegalArgumentException("Content is required")

        context.showNotification(title, content)
        return Result.success()
    }

    companion object{
        fun start(context: Context, match: Match){
            val (id,_,_,team1,team2, matchDate) = match

            val initialDelay = Duration.between(LocalDateTime.now(), matchDate).minusMinutes(5)
            val inputData = workDataOf(
                NOTIFICATION_TITLE_KEY to "Match ${team1.displayName} x ${team2.displayName}",
                NOTIFICATION_CONTENT_KEY to "Match ${team1.flag} x ${team2.flag} is about to start")

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    id,
                    ExistingWorkPolicy.KEEP,
                    createRequest(initialDelay, inputData)
                )
        }

        private fun createRequest(initialDelay: Duration, inputData: Data ): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<NotificationMatchesWorker>()
                .setInitialDelay(initialDelay)
                .setInputData(inputData)
                .build()

        fun cancel(context: Context,  match: Match){
            WorkManager.getInstance(context)
                .cancelUniqueWork(match.id)
        }
    }
}