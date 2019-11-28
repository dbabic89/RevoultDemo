package com.dariobabic.revoultdemo.utils

import android.content.Context
import com.dariobabic.revoultdemo.utils.Constants.CURRENCY_FLAG
import com.dariobabic.revoultdemo.utils.Constants.CURRENCY_NAME
import com.dariobabic.revoultdemo.utils.Constants.DRAWABLE
import com.dariobabic.revoultdemo.utils.Constants.STRING
import java.util.*

object ResourcesUtils {

    fun getCurrencyNameId(context: Context, symbol: String) =
        context.resources.getIdentifier(
            CURRENCY_NAME + symbol.toLowerCase(Locale.ROOT), STRING, context.packageName
        )

    fun getCurrencyFlagId(context: Context, symbol: String) =
        context.resources.getIdentifier(
            CURRENCY_FLAG + symbol.toLowerCase(Locale.ROOT), DRAWABLE, context.packageName
        )
}