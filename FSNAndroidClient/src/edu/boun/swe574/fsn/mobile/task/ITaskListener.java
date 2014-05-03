package edu.boun.swe574.fsn.mobile.task;


public interface ITaskListener {

	<T> void onTaskComplete(TaskResultType type, T result);
}
