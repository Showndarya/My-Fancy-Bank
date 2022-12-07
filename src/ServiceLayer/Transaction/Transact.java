package ServiceLayer.Transaction;

public interface Transact<T,X> {
    public Boolean MakeTransaction(T account, X object);
    public Boolean GetTransactions(T account);
    public Boolean ValidateTransaction(T account, X object);
}
