package com.alorma.github.sdk.services.content;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import android.os.Handler;
import com.alorma.github.sdk.R;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.utils.GitskariosSettings;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 17/12/2014.
 */
public class GetArchiveLinkService extends GithubClient {

	private final Handler handler;
	private OnDownloadServiceListener onDownloadServiceListener;

	private RepoInfo repoInfo;

	public GetArchiveLinkService(Context context, RepoInfo repoInfo) {
		super(context);
		this.repoInfo = repoInfo;
		handler = new Handler();
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		GitskariosSettings settings = new GitskariosSettings(context);
		String zipBall = context.getString(R.string.download_zip_value);
		String fileType = settings.getDownloadFileType(zipBall);
		restAdapter.create(ContentService.class).archiveLink(repoInfo.owner, repoInfo.name, fileType, repoInfo.branch, new Callback<Object>() {
			@Override
			public void success(Object o, Response r) {
				r.getHeaders();
			}

			@Override
			public void failure(RetrofitError error) {
				if (error.getResponse().getReason().equalsIgnoreCase("OK")) {
					String url = error.getResponse().getUrl();
					DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
					DownloadManager.Request request = new DownloadManager.Request(
							Uri.parse(url));

					GitskariosSettings settings = new GitskariosSettings(context);
					
					String zipBall = context.getString(R.string.download_zip_value);
					String fileType = settings.getDownloadFileType(zipBall);
					
					String fileName = repoInfo.name + "_" + repoInfo.branch + "_" + "." + (fileType.equalsIgnoreCase(zipBall) ? "zip" : "tar");
					request.setTitle(fileName);
					request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "gitskarios/" + fileName);
					request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					request.allowScanningByMediaScanner();
					final long enqueue = dm.enqueue(request);

					handler.post(new Runnable() {
						@Override
						public void run() {
							if (onDownloadServiceListener != null) {
								onDownloadServiceListener.onDownloadEnqueded(enqueue);
							}
						}
					});
				}
			}
		});
	}

	@Override
	protected Object executeServiceSync(RestAdapter restAdapter) {
		GitskariosSettings settings = new GitskariosSettings(context);
		String zipBall = context.getString(R.string.download_zip_value);
		String fileType = settings.getDownloadFileType(zipBall);
		return restAdapter.create(ContentService.class).archiveLink(repoInfo.owner, repoInfo.name, fileType, repoInfo.branch);
	}

	public void setOnDownloadServiceListener(OnDownloadServiceListener onDownloadServiceListener) {
		this.onDownloadServiceListener = onDownloadServiceListener;
	}

	public interface OnDownloadServiceListener {
		void onDownloadEnqueded(long id);
	}
}
