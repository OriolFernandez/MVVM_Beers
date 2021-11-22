package com.uriolus.mvvbeers.domain.timeutils

import java.util.concurrent.TimeUnit

class Formatter {
    companion object {
        fun Long.formatToSeconds(): String {
            return String.format("%d", TimeUnit.MILLISECONDS.toSeconds(this))
        }
    }
}