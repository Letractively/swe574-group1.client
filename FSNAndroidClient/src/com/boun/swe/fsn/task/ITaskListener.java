package com.boun.swe.fsn.task;


public interface ITaskListener {

	<T> void onTaskComplete(TaskResultType type, T result);
}
