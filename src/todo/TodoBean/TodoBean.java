package todo.TodoBean;

public class TodoBean {

	private int _id;
	private String detail;
	private boolean done;

	public TodoBean(int _id, String detail, boolean done) {
		super();
		this._id = _id;
		this.detail = detail;
		this.done = done;
	}
	
	public TodoBean() {
		// TODO Auto-generated constructor stub
	}

	public int get_Id() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "TodoBean [_id=" + _id + ", detail=" + detail + ", done=" + done + "]";
	}
	
	

}
