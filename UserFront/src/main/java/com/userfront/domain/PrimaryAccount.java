/**
 * Created by Girish Ananda
 */

package com.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PrimaryAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int accountNumber;
	private BigDecimal accountBalance;
	
	@OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PrimaryTransaction> primaryTransactionList;
/** mappedBy = "primaryAccount" means that the class: PrimaryAccount has a field in the same field name: primaryAccount, which will be used for mapping */
/** fetch = FetchType.LAZY means that when an object of the class is created, the values from the list need not be retrieved unless required */
/** cascade = CascadeType.ALL means that actions on the list will be propagated to the class: PrimaryAccount */
/** @JsonIgnore => List, during serialization references to the class: PrimaryAccount which will reference back to the List which forms an infinite loop. To avoid this loop and stop at returning the class as Json Object, the loop is broken. i.e., the List declaration is skipped after it is processed once. */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<PrimaryTransaction> getPrimaryTransactionList() {
		return primaryTransactionList;
	}

	public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
		this.primaryTransactionList = primaryTransactionList;
	}
	
}
