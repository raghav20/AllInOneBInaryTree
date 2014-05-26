public class Demo{
	public static void main(String args[]){
		int a[] = {2,5,4,7,1,9,3};
		int post[] = {20,8,4,12,10,14,22,25};
		int sum[] ={6,3,5,2,5,7,4};
		BinaryTree bt = new BinaryTree();
		LinkList ll = new LinkList();

		for(int i=0;i<post.length;i++){
			bt.insert(post[i]);

		}
		bt.show();
		ll.add(100);
		ll.add(200);
		ll.add(300);
		ll.add(200);
		ll.add(100);
		ll.add(100);
		//ll.display();

		//Fib f = new Fib();
		//System.out.println(f.getFib3(5));
		
		//System.out.println("has seq..." + bt.hasSequence(post,0,7));
		//System.out.println("has bst..." + bt.isBST());

	}

}
