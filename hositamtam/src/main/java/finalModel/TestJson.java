package finalModel;

public class TestJson {

	public static void main(String[] args) {
		
		PostDAO postDAO = new PostDAO();
		System.out.println(postDAO.getAllPost(100));
	//	System.out.println(postDAO.getPostHot(100));
	}

}
