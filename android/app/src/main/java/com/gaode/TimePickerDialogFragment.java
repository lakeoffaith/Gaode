/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.gaode;

import javax.annotation.Nullable;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.format.DateFormat;


public class TimePickerDialogFragment extends DialogFragment {

  @Nullable
  private OnTimeSetListener mOnTimeSetListener;
  @Nullable
  private OnDismissListener mOnDismissListener;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Bundle args = getArguments();
    return createDialog(args, getActivity(), mOnTimeSetListener);
  }

  /*package*/ static Dialog createDialog(
      Bundle args, Context activityContext, @Nullable OnTimeSetListener onTimeSetListener
  ) {
    final Calendar now = Calendar.getInstance();
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int minute = now.get(Calendar.MINUTE);
    boolean is24hour = DateFormat.is24HourFormat(activityContext);

    if (args != null) {
      hour = args.getInt(TimePickerDialogModule.ARG_HOUR, now.get(Calendar.HOUR_OF_DAY));
      minute = args.getInt(TimePickerDialogModule.ARG_MINUTE, now.get(Calendar.MINUTE));
      is24hour = args.getBoolean(
          TimePickerDialogModule.ARG_IS24HOUR,
          DateFormat.is24HourFormat(activityContext));
    }

    return new com.facebook.react.modules.timepicker.DismissableTimePickerDialog(
        activityContext,
        onTimeSetListener,
        hour,
        minute,
        is24hour);
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    if (mOnDismissListener != null) {
      mOnDismissListener.onDismiss(dialog);
    }
  }

  public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
    mOnDismissListener = onDismissListener;
  }

  public void setOnTimeSetListener(@Nullable OnTimeSetListener onTimeSetListener) {
    mOnTimeSetListener = onTimeSetListener;
  }
}
