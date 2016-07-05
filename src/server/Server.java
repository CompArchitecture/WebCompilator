package server;

import java.util.ArrayList;

public class Server {

	private static Server server;
	private ArrayList<String> result = null;
	private boolean compilationInProgress = false;
	private final Object lock = new Object();

	private Server() {

	}

	public static Server getInstance() {
		if (server == null) {
			server = new Server();
		}
		return server;
	}

	private synchronized void reset() {
		result = null;
		synchronized (lock) {
			compilationInProgress = true;
		}
	}

	public boolean compilationEnded() {
		boolean compilationEnded;
		synchronized (lock) {
			 compilationEnded = !compilationInProgress;
		}
		return compilationEnded;
	}

	public String getResult() {
		String html = "";
		for (int i = 0; i< result.size(); i++ ) {
			html += result.get(i);
			if(i!=result.size() -1)
				html += ";";
		}
		return html;
	}

	public void setResult(ArrayList<String> result) {
		this.result = result;
	}

	public void startCompilation(String path) {
		new Thread(() -> {
			reset();
			result = compile(path);
			synchronized (lock) {
				compilationInProgress = false;
			}
		}).start();
	}

	private  ArrayList<String> compile(String path) {
		System.out.println(path);

		Judge judge = new Judge("/home/guri/GeoCode/WebCompilator/src/tests", path, 800000, 1);
		ArrayList<String> status;
		try {
			status = judge.getStatus();
		} catch (Exception ignored) {
			status = new ArrayList<>();
			status.add("Error Occured");
		}
		System.out.println("compilation ended");
		return status;
	}
}
