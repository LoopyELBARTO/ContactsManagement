import ch07.trees.BSTInterface;
import ch07.trees.BinarySearchTree;

public class SearchDialogController {
    BinarySearchTree<String> bstBusiness = new BinarySearchTree<>();
    Iterable<Business> iter;

    private void loadDataToBST(){
        iter = bstBusiness.getIterator(BSTInterface.Traversal.Inorder);
        while (iter.)
        bstBusiness.add(new Business().getBusinessName());
    }

}
