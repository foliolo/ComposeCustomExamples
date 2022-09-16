package com.ahgitdevelopment.course.customexamples.features.screens.datastore.timer

sealed class TimerEvent {
    object OnTimerClickA: TimerEvent()
    object OnTimerClickB: TimerEvent()
}