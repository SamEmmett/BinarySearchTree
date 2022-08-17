
import java.io.*;


class BinaryTreeTest {

	public static void main(String args[]) {
		BinaryTree<Integer> node6 = new BinaryTree(6, null, null);
		BinaryTree<Integer> node7 = new BinaryTree(7, null, null);
		BinaryTree<Integer> node5 = new BinaryTree(5, node6, node7);
		BinaryTree<Integer> node4 = new BinaryTree(4, null, null);
		BinaryTree<Integer> node2 = new BinaryTree(2, node4, node5);
		BinaryTree<Integer> node3 = new BinaryTree(3, null, null);
		BinaryTree<Integer> node1 = new BinaryTree(1, node2, node3);
		
		//inorder
		System.out.println(node1.preorderToString());

		//test read
		FileReader rd;
		try {
			rd = new FileReader("C:\\Users\\samue\\Documents\\Java Workspace\\Lab7\\input.txt");
			System.out.print (BinaryTree.readBinaryTree(new BufferedReader(rd)).inorderToString());
			
		}catch (FileNotFoundException e) { e.printStackTrace();
		}catch (IOException e) { e.printStackTrace();}
	}
	
}
