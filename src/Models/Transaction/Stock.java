package Models.Transaction;

import Enums.TransactionType;

public class Stock extends Transaction {
    public int client_id;
    public int stock_id;
    public int price;
    public int number_of_shares;
}
