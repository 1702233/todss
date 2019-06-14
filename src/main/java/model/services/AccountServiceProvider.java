package model.services;

public class AccountServiceProvider {

private static AccountService service = new AccountService();
	
	public static AccountService getAccountService() {
		return service;
	}
}
