package csseminar.scene;

public interface ProgressObserver {
	
	public void start();
	public void progress(double pccomplete);
	public void finish();
}
