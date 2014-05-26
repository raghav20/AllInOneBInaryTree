import java.util.Stack;
import java.util.*;
import java.util.Queue;

class BTreeNode{
	 public int value;
	
	 public BTreeNode lChild;
	 public BTreeNode rChild;
	 boolean visited;
	 public BTreeNode(int value){
	 	this.lChild = null;
	 	this.rChild = null;
	 	this.value = value;
	 	this.visited = false;
	 	
	 }
	 public boolean add(int value){
	 	BTreeNode btn = new BTreeNode(value);
	 	if(value == this.value){
	 		return false;
	 	}
	 	if(value<this.value){
	 		if(this.lChild == null){
	 			this.lChild = btn;
	 			return true;
	 		}
	 		else{
	 			lChild.add(value);
	 		}
	 	}
	 	else{
	 		if(this.rChild == null){
	 			this.rChild = btn;
	 			return true;
	 		}
	 		else{
	 			return rChild.add(value);
	 		}
	 	}
	 	return false;
	 }

}

public class BinaryTree{
    BTreeNode root;
    public ArrayList<BTreeNode>bt ;
	public BinaryTree(){
		root = null;
		bt = new ArrayList<BTreeNode>();
	}
	public boolean insert(int value){
		if(root == null){
			root  = new BTreeNode(value);
			return true;
		}
		else{
			return root.add(value);
		}
	}
	
	//Inorder 
	public void inorder(BTreeNode node){
		System.out.println("Value inorder" + node.value);
		if(node.lChild!=null)
			inorder(node.lChild);
		
		if(node.rChild!=null)
			inorder(node.rChild);
	}

	//postorder
	public void postorder(BTreeNode node){
		if(node.lChild!=null)
			inorder(node.lChild);
		if(node.rChild!=null)
			inorder(node.rChild);
		System.out.println("Value is" + node.value);
	}

	//hasPathSum
	public boolean  hasPathSum(int data){
		return hasPathSum(this.root,data);
	}

	//isBST
	public boolean isBST(){
		return isBST(this.root);
	}

	//hasPathSum
	public boolean hasPathSum(BTreeNode node,int diff){
		if(node == null)return false;
		else{
			diff = diff - node.value;
			//System.out.println("digg...." + diff);
			if(node.lChild == null && node.rChild == null){
				return (diff == 0);
			}
			else{
				if(node.lChild!=null && hasPathSum(node.lChild,diff)) return true;
				if(node.rChild!=null && hasPathSum(node.rChild,diff)) return true;
			}
			return false;
		}
		
	}
	
	//dfs
	public void dfs(BTreeNode root){
		Stack<BTreeNode> s = new Stack<BTreeNode>();
		s.push(root);
		while(!s.isEmpty()){
			BTreeNode n = s.pop();
			System.out.println(n.value);
			if(n.rChild!=null)s.push(n.rChild);
			if(n.lChild!=null)s.push(n.lChild);

		}
		
	}

	
	//printPath
	public void printPath(BTreeNode node){
		Stack<BTreeNode> s = new Stack<BTreeNode>();
		s.push(node);
		while(!s.isEmpty()){
			BTreeNode b = s.pop();
			//System.out.println(b.value);
			if(b!=null && b.lChild == null && b.rChild == null){
				//printPath(bt);
				
				//bt.add(b);
				printPath(bt);
				System.out.println("ooo  "+b.value);
			}
			else{
				bt.add(b);
			}
			if(b.rChild!=null) s.push(b.rChild);
			if(b.lChild!=null) s.push(b.lChild);
		}

	}


	public void printPath(ArrayList< BTreeNode> b){
		for(BTreeNode n : b ){
			System.out.println("ooo     "+ n.value);
		}
		//bt.remove(b.size()-1);
		
	}

	//hasSequence
	public boolean hasSequence(int seq[],int start, int len ){
		if(len<=0 || seq==null){
			return false;
		}
		int root = seq[len-1];
		int i=start;
		for(;i<len-1;i++){
			if(seq[i] >= root)break;
		}
		for(int j=i;j<len-1;j++){
			if(seq[j] <= root)return false;
		}
		boolean left =true;
		boolean right = true;
		if(i>0){
			left = hasSequence(seq,0,i);
		}
		if(i<len-1){
			right = hasSequence(seq,i,len-1-i);
		}
		return left && right;

	}

	//mirror
	public  BTreeNode mirror(BTreeNode node){
		BTreeNode temp = new BTreeNode(node.value);
		if(node == null)return null;
		if(node.lChild!=null){
			temp.rChild = mirror(node.lChild);
		}
		if(node.rChild!=null){
			temp.lChild = mirror(node.rChild);		}
		return temp;
	}

	//isBST
	public boolean isBST(BTreeNode node){
		if(node == null) return true;
		//System.out.println("bst1...." + getMin(node.lChild,1000));
		//System.out.println("bst.2..." + getMax(node.rChild,0));
		if(node.lChild!=null){
			if(node.value < getMin(node.lChild,0)){
				//System.out.println("bst...." + getMin(node.lChild,0));
				return false;
				
			} 
		}
		if(node.rChild!=null){
			if(node.value > getMax(node.rChild,0)) {
				//System.out.println("bst...." + getMin(node.rChild,0));
				return false;
			}
		}
		if(!isBST(node.lChild) && !isBST(node.rChild)) return false;

		else return true;
	}

	//getMin
	public int getMin(BTreeNode node, int min){
		//if(node == null) return 0;
		if(node.value < min) {
			min = node.value;
		}
		if(node.lChild!=null) return getMin(node.lChild,min);
		if(node.rChild!=null) return getMin(node.rChild,min);
		return min;
	}

	//getMax
	public int getMax(BTreeNode node, int max){
		if(node == null) return 0;
		if(node.value > max) max = node.value;
		if(node.lChild!=null) return getMin(node.lChild,max);
		if(node.rChild!=null) return getMin(node.rChild,max);
		return max;
	}

	//findAncestor
	public BTreeNode findAncestor(BTreeNode node, BTreeNode p,BTreeNode q){
		if(node == null)return null;
		if(node ==p || node == q) return node;
		//if(node.lChild == p && node.rChild == q || node.lChild == q && node.rChild == p ) return node;
		BTreeNode left = findAncestor(node.lChild,p,q);
		BTreeNode right = findAncestor(node.rChild,p,q);
		if(left!=null && right!=null) return node;
		if(left!=null) return left;
		else return right;
	}

	//inOrderI
	public void inOrderI(BTreeNode node){
		Stack<BTreeNode> s = new Stack<BTreeNode>();
		boolean visited  = false;
		while(!visited){
			if(node!=null){
				s.push(node);
				node = node.lChild;
			}
			else{
				if(!s.isEmpty()){
					BTreeNode b = s.pop();
					System.out.println(b.value);
					node = b.rChild;
				}
				else{
					visited = true;
				}
			}
		}
	}

	//preOrder
	public void preOrder(BTreeNode node){
		Stack<BTreeNode> s = new Stack<BTreeNode>();
		boolean visited  = false;
		while(!visited){
			if(node!=null){
				System.out.println("Value in preOrder "+node.value);
				s.push(node);
				node = node.lChild;

			}
			else{
				if(!s.isEmpty()){
					node = s.pop();
					node = node.rChild;
				}
				else{
					visited = true;
				}
			}
		}
	}


	//show
	public void show(){
		if(root == null){
			System.out.println("Nothing to display");
		}
		else{
			inorder(this.root);
			BTreeNode temp = mirror(this.root);
			//inorder(temp);
			//dfs(this.root);
			printPath(this.root);
			postorder(this.root);
			isPair(this.root,3);
			inOrderI(this.root);
			inorder(this.root);
			preOrder(this.root);
			boundry(this.root);
			BTreeNode n1 = this.root.lChild;
			BTreeNode n2 = this.root.rChild.rChild;
			System.out.println("test.."+n1.value+"...."+n2.value);
			//bfs(this.root);
			System.out.println("ancestor"+findAncestor(this.root,n1,n2).value);
			spiralOrder(this.root);
			kDistance(n1,n2);
			System.out.println(isBalanced(this.root));
			
			//rightView(this.root);
			System.out.println("sum "+sum(this.root,0));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 12 "+rMember(this.root,12));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 22 "+rMember(this.root,22));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 25 "+rMember(this.root,25));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 14 "+rMember(this.root,14));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 4 "+rMember(this.root,4));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 20 "+rMember(this.root,20));
			System.out.println("_______________________________________________________________________");
			System.out.println("right 8 "+rMember(this.root,8));

		}
	}

	//inSucc
	public int inSucc(BTreeNode b, int value){
		BTreeNode target = search(b,value);
	
		if(target ==null)return 0;
		if(target.rChild!=null){
			target = target.rChild;
			while(target.lChild!=null)target = target.lChild ;
			return target.value;

		}
		else{
			BTreeNode successor = null;
			BTreeNode ancestor = this.root;
			while(ancestor!=target){
				if(target.value < ancestor.value){
					successor = ancestor;
					ancestor = ancestor.lChild;	
				}
				else{
					ancestor = ancestor.rChild;
				}
			}
			return successor.value;
		}
	} 

	//search
	public BTreeNode search(BTreeNode node,int data){
		if(node == null)return null;
		else{
			if(node.value == data)return node;
			if(node.value > data) return search(node.lChild,data);
			if(node.value < data) return search(node.rChild,data);
			else return null;
		}
	}

	//printLeaves
	public void printLeaves(BTreeNode node){
		if(node!=null){
			if(node.lChild==null && node.rChild ==null){
				System.out.println("leaf"+node.value);
			}
			printLeaves(node.lChild);
			printLeaves(node.rChild);
		}
	}

	//isLeave
	public boolean isLeave(BTreeNode node){
		if(node.lChild ==null && node.rChild == null){
			return true;
		}
		else{
			return false;
		}
	}

	//boundry
	public void boundry(BTreeNode node){
		BTreeNode curr = node;
		while(node.lChild!=null && !isLeave(node)){
			System.out.println("lchild.."+node.value);
			node = node.lChild;
		}
		printLeaves(this.root);
		Stack<BTreeNode> s = new Stack<BTreeNode>();
		while(curr.rChild!=null && !isLeave(curr)){
			//System.out.println("loop.."+curr.value);
			s.push(curr.rChild);
			curr = curr.rChild;
		} 
		while(!s.isEmpty()){
			BTreeNode bt =  s.pop();
			System.out.println("right.."+bt.value);
		}

	}

	//BFS
	public void bfs(BTreeNode node){
		Queue<BTreeNode> q = new LinkedList<BTreeNode>();
		//int arr[] = new arr[9];
	
		q.add(node);
		//int i=0;
		while(!q.isEmpty()){
			BTreeNode b =  q.remove();
			if(!b.visited)System.out.println("queue added.."+b.value);
			b.visited = true;
			if(b.lChild!=null) q.add(b.lChild);
			if(b.rChild!=null) q.add(b.rChild);
		}

	}

	//spiralOrder
	public void spiralOrder(BTreeNode node){
		Stack<BTreeNode> s1 = new Stack<BTreeNode>();
		Stack<BTreeNode> s2 = new Stack<BTreeNode>();
		s1.push(node);
		while(!s1.isEmpty() || !s2.isEmpty()){
			while(!s1.isEmpty()){
				BTreeNode b = s1.pop();
				if(!b.visited)System.out.println("LTR" + b.value);
				b.visited = true;
				if(b.rChild!= null)s2.push(b.rChild);
				if(b.lChild!=null)s2.push(b.lChild);
			}
			while(!s2.isEmpty()){
				BTreeNode b = s2.pop();
				if(!b.visited)System.out.println("RTL" + b.value);
				b.visited = true;
				if(b.lChild!= null)s1.push(b.lChild);
				if(b.rChild!=null)s1.push(b.rChild);
			}	
		}
		
		
	}

	//isPair
	public boolean isPair(BTreeNode root,int target){
		Stack<BTreeNode> s1 = new Stack<BTreeNode>();
		Stack<BTreeNode> s2 = new Stack<BTreeNode>();
		int val1 =0;
		int val2 =0;
		boolean b1 =false;
		boolean b2= false;
		BTreeNode curf = root;
		BTreeNode curr = root;
		while(true){
			while(!b1){
				if(curf!=null){
					s1.push(curf);
					curf = curf.lChild;
				}
				else{
					if(s1.isEmpty()){
						b1 = true;
					}
					else{
						curf = s1.pop();
						val1 = curf.value;
						curf = curf.rChild;
						b1 = true;
					}
				}
			}
			while(!b2){
				if(curr!=null){
					s2.push(curr);
					curr = curr.rChild;
				}
				else{
					if(s2.isEmpty()){
						b2 = true;
					}
					else{
						curr = s2.pop();
						val2 = curr.value;
						curr = curr.lChild;
						b2 = true;
					}
				}
			}
			if((val1 != val2) && (val1 +val2 ==target)){
				System.out.println("Pair Found...."+val1 + "Pair Found..." + val2);
				return true;
			}
			else if ((val1+val2) > target) {
					b2 = false;
				}
			else if((val1+val2) < target){
					b1 = false;
				}
			
			if(val1 >= val2)
			{
				System.out.println("Pair Not Found...."+val1 + "Pair Not Found..." + val2);
				return false;	

			}
					

		}
		
	}

	//kDistance
	public void kDistance(BTreeNode n1, BTreeNode n2){
		BTreeNode b = findAncestor(this.root,n1,n2);
		int dist = getDistance(n1) - 2 * getDistance(b) + getDistance(n2); 
		System.out.println("Distance.."+ dist);

	}

	//getHeight
	public int getHeight(BTreeNode n1){
		if(n1 == null)return 0;

		else return 1+Math.max(getHeight(n1.lChild),getHeight(n1.rChild));
	}

	//getDistance
	public int getDistance(BTreeNode node){
		int height = 1;
		BTreeNode temp = this.root;
		//if(temp == root)return 1;
		while(temp!=node){
			if(node.value < temp.value){
				if(temp.lChild!=null){
					temp = temp.lChild;
					height++;
				}
			}
			else{
				if(temp.rChild!=null){
					temp = temp.rChild;
					height++;
				}
				
			}
		}
		return height;
	}

	//isBalanced
	public boolean isBalanced(BTreeNode node){
		if(node == null) return true;
		int diff =  getHeight(node.lChild) -getHeight(node.rChild);
		if (diff < 0) {
    		diff *= -1;
		}
		if(diff <= 1 && isBalanced(node.rChild) && isBalanced(node.lChild)) return true;
		else return false;
	}

	//rightView
	public void rightView(BTreeNode node){
		int max_level = 0;
		rightViewUtil(node,1,max_level);
	}


	public void rightViewUtil(BTreeNode node, int level,int max_level){
		if(root == null)return;
		//System.out.println("level"+level + "max"+max_level);
		if(max_level < level){
			System.out.println("level"+level + "data"+node.value);
			max_level = level;
		}
		if(node.rChild!=null) rightViewUtil(node.rChild,level+1,max_level);
		if(node.lChild!=null) rightViewUtil(node.lChild,level,max_level);
	}

	//sum 
	public int sum(BTreeNode node ,int val){
		if(node == null) return 0;
		val = node.value + 10*val;
		
		if(node.lChild == null  && node.rChild ==null){
			System.out.println("node "+ node.value + " Vlaue  " + val);
			return val;
		}
		else return sum(node.lChild,val)+ sum(node.rChild,val);
		//if(root.lChild ==null && root.rChild == null) return val;
		
	}

	

	public int rMember(BTreeNode root,int key){
		if(root ==null)return -1;
		Queue<BTreeNode> q = new LinkedList<BTreeNode>();
		int out = -1;
		boolean isFlag = true;
		if(root.value == key) return key;
		q.add(root);
		//q.add(null);
		while(!q.isEmpty()){
			BTreeNode bt = q.remove();
			BTreeNode bq = q.peek();
			if(bt!=null){
				if(bt.value == key){
					//System.out.println("_______________________________________________________________________");
					System.out.println("rightValue "+ bt.value);
					BTreeNode next = q.element();
					if(next == null) out =-1;
					else {
						out = next.value;
						System.out.println("rightView "+ next.value);
					}
				}
				else{
					if(bt.lChild!=null)q.add(bt.lChild);
					if(bt.rChild!=null)q.add(bt.rChild);
					if(isFlag){
						q.add(null);
						isFlag = false;
					}
					if(bq ==null)q.add(null);  
					System.out.println("Not Equal "+ bt.value);	
					//System.out.println("_______________________________________________________________________");
				}
				
			}

		}
		return out;
	}

}
