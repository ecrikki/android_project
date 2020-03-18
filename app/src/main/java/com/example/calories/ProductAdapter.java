package com.example.calories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<Products> list;

    public ProductsAdapter(List<Products> list){
        this.list = list;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Products product = list.get(position);
        holder.textProduct.setText(product.белки);
        holder.textKkal.setText(product.калорийность);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{
        TextView textProduct, textKkal;

        public ProductsViewHolder(View itemView){
            super(itemView);

            //textProduct = (TextView) itemView.findViewById(R.id.text_product);
            //textKkal = (TextView) itemView.findViewById(R.id.text_kkal);
        }
    }
}
