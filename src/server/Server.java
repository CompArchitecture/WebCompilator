package server;

public class Server {

	private static Server server;
	private String result = null;
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
		return result;
	}

	public void setResult(String result) {
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

	private String compile(String path) {
		System.out.println(path);

		Judge judge = new Judge("/home/guri/GeoCode/WebCompilator/src/tests", path, 800000, 1);
		String status;
		try {
			status = judge.getStatus();
		} catch (Exception ignored) {
			status = "Error occurred";
		}
		System.out.println("compilation ended");
		return status;
	}
}
