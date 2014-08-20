import java.io.*;
public class Demo{
   public static void main(String args[]){
      try{
        BTree bt = new BTree();

        int s[] = {10,6,14,5,8,11,18};
        //int s[]= {20,8,4,10,22,12,14,25};

        for(int a:s){
          bt.insert(a);
        }
        bt.inOrder(bt.root);
        //bt.getDFS(bt.root);
        //bt.hasPathSum(bt.root,42);
        //bt.printLeaves(bt.root);
        //bt.inOrderI(bt.root);
        //bt.bfs(bt.root);
        //bt.boundry(bt.root);
        //bt.inOrder(bt.root);
        bt.show(bt.root);
        //System.out.println("\ntest "+   bt.maxPathLeaf(bt.root,0) +" "+ bt.min);
      }
      catch(Exception e){
        e.printStackTrace();
      }

   }
}
