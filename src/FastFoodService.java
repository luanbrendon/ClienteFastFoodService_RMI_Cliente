import java.rmi.Remote;
import java.util.List;

public interface FastFoodService extends Remote {
    List<Produto> getItensDisponiveis() throws java.rmi.RemoteException;
    void selectItem(Produto produto) throws java.rmi.RemoteException;
    List<Produto> getSelectedItems() throws java.rmi.RemoteException;
    double getTotalAmount() throws java.rmi.RemoteException;
    void pay(double amountPaid) throws java.rmi.RemoteException;
}