public class TestBinarySearchTree {
	
	public static void main(String args[])
	{
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(50);
		bst.add(40);
		bst.add(80);
		bst.add(20);
		bst.add(45);
		bst.add(60);
		bst.add(90);
		bst.add(55);
		bst.add(65);
		
		System.out.println("After addition: Inorder traversal for the Binary Search Tree ");
		System.out.print(bst.inorderToString());
		
		System.out.println("\r\n");
		
		bst.delete(20);
		bst.delete(80);

		System.out.println("After removal: Inorder traversal for the Binary Search Tree ");
		System.out.print(bst.inorderToString());
		
		System.out.println("Find 65 in the tree. ");
		if(bst.find(65)==null) System.out.println("It is NOT FOUND!");
		else System.out.println("It is FOUND!");
		
		System.out.println("Find 80 in the tree. " );
		if(bst.find(80)==null) System.out.println("It is NOT FOUND!");
		else System.out.println("It is FOUND!");
		
	}
}