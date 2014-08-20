//Node Class
import java.util.*;
class BTreeNode{
  BTreeNode lChild;
  BTreeNode rChild;
  boolean islVisited;
  boolean isrVisited;
	int value;
  //Constructor
	public BTreeNode(int value){
		lChild = null;
		rChild = null;
		this.value = value;
	}

  //inserting at appropriate place
  protected boolean add(int value){
    BTreeNode btn = new BTreeNode(value);
    if(this.value == value) return false;
    if(this.value > value){
      if(this.lChild == null){
        this.lChild = btn;
        return true;
      }
      else lChild.add(value);
    }
   else{
     if(this.rChild ==null){
       rChild = btn;
       return true;
     }
     else{
       rChild.add(value);
     }
   }
   return false;
  }

}

//Main Class
public class BTree{
  BTreeNode root;
  static int min=0;
  Hashtable<Integer,ArrayList<BTreeNode>>ht;
  //Constructor
  BTree(){
    root  = null;
    ht = new Hashtable<Integer,ArrayList<BTreeNode>>();
  }

  //inserting values into Binary Tree
  public boolean insert(int value){
    if(root == null){
      root = new BTreeNode(value);
      return true;
    }
    else{
      return root.add(value);
    }
  }

  //preOrder traversal
  public void preOrder(BTreeNode node){
    if(node ==null)return;
    System.out.println("preOrder "+ node.value);
    preOrder(node.lChild);
    preOrder(node.rChild);
  }

  //inOrder traversal
  public void inOrder(BTreeNode node){
    if(node ==null)return;
    inOrder(node.lChild);
    System.out.println("InOrder "+ node.value);
    inOrder(node.rChild);
  }

  //Binary Tree Balanced if height diff bwn left and right subtree not more then 1
  public boolean isBalanced(BTreeNode node){
      /*if(node ==null)return true;
      int diff = getHeight(node.lChild) - getHeight(node.rChild);
      if(Math.abs(diff)>1)return false;
      else return isBalanced(node.lChild) && isBalanced(node.rChild);*/
      if(checkHeight(root)==-1)return false;
      else return true;
  }

  //height of binary Tree
  public int getHeight(BTreeNode node){
    if(node ==null) return 0;
    return Math.max(getHeight(node.lChild),getHeight(node.rChild))+1;
  }

  //checkHeight and return -1 if tree unbalanced else return height
  public int checkHeight(BTreeNode node){
    if(node ==null) return 0;
    int leftH = checkHeight(node.lChild);
    if(leftH == -1)return -1;
    int rightH = checkHeight(node.rChild);
    if(rightH == -1)return -1;
    int diff = leftH - rightH;
    if(Math.abs(diff)>1) return -1;
    else return Math.max(checkHeight(node.lChild),checkHeight(node.rChild)+1);
  }

  //finds ancestor of two nodes
  public BTreeNode findAncestor(BTreeNode node,BTreeNode p,BTreeNode q){
    if(node ==null)return null;
    if(node ==p || node ==q)return node;
    BTreeNode l = findAncestor(node.lChild,p,q);
    BTreeNode r = findAncestor(node.rChild,p,q);
    if(l!=null && r!=null) return node;
    if(l!=null)return l;
    else return r;
  }

  //checking if the sum of nodes in tree is equal to specified sum
  public boolean hasPathSum(BTreeNode node , int sum){
    if(node == null)return false;
    int diff = sum-node.value;
    if(node.lChild==null && node.rChild==null){
      if(diff ==0)printPath(node);
      return (diff ==0);
    }
    if(node.lChild!=null && hasPathSum(node.lChild,diff))return true;
    if(node.rChild!=null && hasPathSum(node.rChild,diff))return true;
    return false;
  }

  //print path from root to leaf of specified sum
  public ArrayList<BTreeNode> printPath(BTreeNode node){
     ArrayList<BTreeNode>btn = new ArrayList<BTreeNode>();
     btn.add(node);
     while(node.value!=root.value){
       node = getParent(node);
       btn.add(node);
     }
     //btn.add(this.root);
     for(BTreeNode bt:btn)System.out.print(bt.value+" ");
     return btn;
  }

  //getParent of any node in Binary Tree
  public BTreeNode getParent(BTreeNode node){
    BTreeNode temp =this.root;
    BTreeNode prev =temp;
    if(temp.value ==node.value)return node;
    while(node.value !=temp.value){
      prev = temp;
      if(node.value > temp.value) temp = temp.rChild;
      else temp = temp.lChild;
    }
    return prev;
  }

  //print nodes in DFS order
  public void getDFS(BTreeNode node){
    Stack<BTreeNode>s = new Stack<BTreeNode>();
    BTreeNode[]btn =new BTreeNode[7];
    int i=0;
    s.push(node);
    while(!s.isEmpty()){
      BTreeNode b = s.pop();
      btn[i++] = b;
      if(b.rChild!=null)s.push(b.rChild);
      if(b.lChild!=null)s.push(b.lChild);
    }

  }

  //getDistance of the node from max height
  public int getDistance(BTreeNode node){
    if(node ==null)return 0;
    else return Math.max(getDistance(node.lChild),getDistance(node.rChild))+1;
  }

  //height of a node from root
  public int getDiff(BTreeNode node){
    return getHeight(this.root) - getDistance(node);
  }

  //getDistance between 2 nodes.
  public int getDistance(BTreeNode p,BTreeNode q){
    if(p ==this.root && q!=this.root) return getHeight(q);
    if(q ==this.root && p!=this.root) return getHeight(p);
    if(p==q) return 0;
    else{
      BTreeNode anc = findAncestor(this.root,p,q);
      int d = Math.abs(getDiff(anc)-getDiff(p)-getDiff(q));
      return d;
    }

  }

  //print leaves in a tree
  public void printLeaves(BTreeNode node){
    if(node.lChild==null && node.rChild==null)System.out.print("Leaves " + node.value +" ");
    if(node.lChild!=null)printLeaves(node.lChild);
    if(node.rChild!=null)printLeaves(node.rChild);
  }

  //getClosest node for a given target
  public int getClosest(BTreeNode node,int target){
    if(node.lChild!=null)getClosest(node.lChild,target);
    if(node.rChild!=null)getClosest(node.rChild,target);
    int diff = Math.abs(node.value-target);
    int tar = Math.abs(min-target);
    int mid = Math.min(diff,tar);
    mid=(mid== diff)?node.value:mid;
    min = getNear(target,mid,min);
    return min;
  }

  //getNearest value of the target
  private int getNear(int target,int p,int q){
    int diff1 = Math.abs(target-p);
    int diff2 = Math.abs(target-q);
    int close = (diff1<diff2)?p:q;
    return close;
  }

  //program to print mirror image of a Binarytree
  public BTreeNode mirrorTree(BTreeNode node){
    BTreeNode temp = new BTreeNode(node.value);
    if(node ==null)return  null;
    if(node.lChild!=null)temp.rChild = mirrorTree(node.lChild);
    if(node.rChild!=null)temp.lChild = mirrorTree(node.rChild);
    return temp;
  }

  //rightNeighbour of a node
  public BTreeNode rightNeighbour(BTreeNode node,BTreeNode root){
    Queue<BTreeNode> q = new LinkedList<BTreeNode>();
    BTreeNode temp = new BTreeNode(-1);
    q.add(root);
    q.add(temp);
    while(!q.isEmpty()){
       BTreeNode b = q.remove();
       System.out.println("right "+b.value);
       if(b==node){
         b = q.remove();
         System.out.println("equal "+b.value);
         return b;
       }
       if(b==temp){
          System.out.println("temp "+b.value);
          q.add(temp);
          continue;
          //b = q.remove();
       }
       if(b.lChild!=null)q.add(b.lChild);
       if(b.rChild!=null)q.add(b.rChild);
       //q.add(temp);
    }
    return null;
  }

  //Inorder traversal iterative
  public void inOrderI(BTreeNode node){
    Stack<BTreeNode>s = new Stack<BTreeNode>();
    if(node==null)return;
    boolean isLeaf = false;
    s.push(node);
    while(!s.isEmpty()){
      BTreeNode b = s.pop();
      if((b.lChild==null && b.rChild==null)){
        b.islVisited=true;
        b.isrVisited = true;
        isLeaf = true;
      }

      if(b.islVisited && b.isrVisited){
        System.out.println("visit "+b.value);
        isLeaf = false;
        continue;
      }
      if(b.rChild!=null)s.push(b.rChild);
      b.isrVisited = true;
      if(!isLeaf)s.push(b);
      if(b.lChild!=null)s.push(b.lChild);
      b.islVisited=true;

    }
  }

  //bfs
  public void bfs(BTreeNode node){
    Queue<BTreeNode> q = new LinkedList<BTreeNode>();
    if(node==null) return;
    q.add(node);
    while(!q.isEmpty()){
      BTreeNode b = q.remove();
      if(!b.islVisited) {
        System.out.println("node "+b.value);
        b.islVisited = true;
      }
      if(b.lChild!=null)q.add(b.lChild);
      if(b.rChild!=null)q.add(b.rChild);
    }
  }

  //isLeaf
  public boolean isLeaf(BTreeNode node){
    if(node.lChild ==null  && node.rChild==null)return true;
    else return false;
  }
  //boundry of a tree
  public void boundry(BTreeNode node){
    if(node == null)return;
    Stack<BTreeNode>s = new Stack<BTreeNode>();
    BTreeNode temp = node;
    //s.push(temp);
    while(!isLeaf(node) && node.lChild!=null){
      System.out.print("Boundry "+node.value + " ");
      node = node.lChild;
    }
    while(temp.rChild!=null && !isLeaf(temp.rChild)){
      temp = temp.rChild;
      //System.out.println("o "+temp.value);
      s.push(temp);
    }
    printLeaves(this.root);
    while(!s.isEmpty()){
      BTreeNode b= s.pop();
      System.out.print("Right "+b.value+" ");
    }
  }

  //maximum path sum
  public int maxSum(BTreeNode node,int sum){
    int max  = sum+node.value;
    if(node.lChild ==null && node.rChild==null)return max;
    int sum1 = maxSum(node.lChild,max);
    int sum2 = maxSum(node.rChild,max);
    return (sum1>sum2)?sum1:sum2;
  }

  //maximum path sum between two leaves
  public int maxPathLeaf(BTreeNode node,int sum){
   // if(node ==null)return min;
    if(node.lChild==null && node.rChild==null)return node.value;
    int l=0;
    int r=0;
    if(node.lChild!=null)l = maxPathLeaf(node.lChild,sum);
    if(node.rChild!=null)r = maxPathLeaf(node.rChild,sum);
    int big = l+r+node.value;
    int res = Math.max(Math.max(l,r),big);
    if(res>sum)sum = res;
    System.out.println("\nvalue "+res+ " left "+l+" right "+r+" big "+big);
    System.out.println("\nvalue "+res+ " left "+l+" right "+r);
    min =res;
    return Math.max(l,r)+node.value;
  }

  //transform a BST to greater sum tree
  public void transformTree(BTreeNode node,int sum){
    sum = node.value+sum;
    if(node==null)return;
    if(node.rChild!=null){
      transformTree(node.rChild,sum);
    }

    node.value = sum -node.value;
    if(node.lChild!=null)transformTree(node.lChild,sum);
    //inOrder(this.root);
  }

  //function to directly call function of this class
  public void show(BTreeNode node){
    inVertical(node,0);
    printVertical(ht);
    //transformTree(node,0);
    //inOrder(node);
    System.out.println("............................................................................");
    System.out.println(inOrderSuccessor(root,this.root.lChild.lChild).value);
    System.out.println("............................................................................");
    int a[] ={1,2,4,5,6,8,9};
    System.out.println("............................................................................");
    inOrder(arraytoTree(a,0,6));
    //ArrayList<LinkedList<BTreeNode>> al = getList(root);
    System.out.println("............................................................................");
   // System.out.println("leng" + al.size());
    Hashtable<Integer,LinkedList<BTreeNode>> ht = getList(this.root);
    Set<Integer> keys = ht.keySet();
    for(Integer s: keys){
      LinkedList<BTreeNode>l = ht.get(s);
      for(BTreeNode b:l)System.out.println("Level "+s+" Value "+ b.value);
    }
  }
  //print tree in vertical order
  public void inVertical(BTreeNode node,int hd){
    if(node==null)return;
    if(node.lChild!=null)inVertical(node.lChild,hd-1);
    ArrayList<BTreeNode>al;
    //al = ht.get(hd);
    if(ht.get(hd) == null){
      al = new ArrayList<BTreeNode>();
      al.add(node);
    }
    else{
      al = ht.get(hd);
      al.add(node);
    }
    ht.put(hd,al);
    if(node.rChild!=null)inVertical(node.rChild,hd+1);
  }
  
  //print tree vertically
  public void printVertical(Hashtable hash){
    Enumeration names;
    names = hash.keys();
    int k;
    while(names.hasMoreElements()) {
      k = (Integer) names.nextElement();
      ArrayList<BTreeNode> al = (ArrayList<BTreeNode>)hash.get(k);
      for(BTreeNode b:al)System.out.print("key "+k+" value "+b.value+" ");
      System.out.println("\n");
    }
  }
  
  //inOrderSuccessor
  public BTreeNode inOrderSuccessor(BTreeNode node,BTreeNode tmp){
    BTreeNode t = tmp;
    BTreeNode succ = null;
    BTreeNode anc = this.root;
    if(node==null || tmp ==null)return null;
    if(t.rChild!=null){
      t=t.lChild;
      while(t.lChild!=null)t=t.lChild;
      return t;
    }
    else{
      while(anc!=tmp){
        if(tmp.value<anc.value){
          succ = anc;
          anc =anc.lChild;
          System.out.println("ha ha "+succ.value);
        }
        else{
          anc = anc.rChild;
        }
      }
      return succ;
    }
    
  }
  
  //given sorted array create Binary Tree of minimal height from iterative
  public BTreeNode arraytoTree(int a[],int start,int end){
    if(start > end)return null;
    int inorder[] = Arrays.copyOfRange(a, start, end);
    int mid = (start+end)/2;
    BTreeNode b = new BTreeNode(a[(start+end)/2]);
    b.lChild = arraytoTree(a,start,mid-1);
    b.rChild = arraytoTree(a,mid+1,end);
    System.out.println("value of node "+b.value);
    return b;
  }
  
  //generate Linkedlist for each level
  public Hashtable<Integer,LinkedList<BTreeNode>> getList(BTreeNode node){
    Queue<BTreeNode> q = new LinkedList<BTreeNode>();
    BTreeNode tmp = new BTreeNode(-1);
    Hashtable<Integer,LinkedList<BTreeNode>>al = new Hashtable<Integer,LinkedList<BTreeNode>>();
    q.add(node);
    q.add(tmp);
    LinkedList<BTreeNode> l = new LinkedList<BTreeNode>();
    int level=1;;
    while(!q.isEmpty()){
      BTreeNode b = q.remove();
      
      if(b!=tmp){
         l.add(b);
         //System.out.println(b.value);
         if(b.lChild!=null)q.add(b.lChild);
         if(b.rChild!=null)q.add(b.rChild);
         if(q.peek() == tmp){
            q.add(tmp); 
         }
      }
      else{
       // b = q.remove();
        al.put(level,l);
        l = new LinkedList<BTreeNode>();
        level++;
        //System.out.println("tmp "+ b.value);
        //al.add(l);
      }
      
    }
    return al;
  }

}
