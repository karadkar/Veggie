package com.greentopli.app.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greentopli.app.R;
import com.greentopli.core.presenter.cart.CartCheckoutPresenter;
import com.greentopli.core.presenter.cart.CartView;
import com.greentopli.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartItemsFragment extends Fragment implements CartView{
	@BindView(R.id.cartItem_fragment_recyclerView) RecyclerView mRecyclerView;

	private CartCheckoutPresenter mPresenter;
	private RecyclerView.LayoutManager mLayoutManager;
	private BrowseAdapter mAdapter;

	public CartItemsFragment() {
		// Required empty public constructor
	}


	public static CartItemsFragment newInstance() {
		CartItemsFragment fragment = new CartItemsFragment();
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mPresenter = CartCheckoutPresenter.bind(this,getContext());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView =  inflater.inflate(R.layout.fragment_cart_items, container, false);
		ButterKnife.bind(this,rootView);
		mAdapter = new BrowseAdapter(true);
		mLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mAdapter);
		mPresenter.getProductsFromCart();
		return rootView;
	}

	@Override
	public void onCartCheckoutSuccess() {

	}

	@Override
	public void onCartCheckoutFailed(List<String> failedProductIds) {

	}

	@Override
	public void onCartCheckoutError(String error_message) {

	}

	@Override
	public void onCartItemsReceived(List<Product> cartItems) {
		mAdapter.addNewProducts(cartItems);
	}

	@Override
	public void showProgressbar(boolean show) {

	}
}