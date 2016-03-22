package net.infojobs.workshop4.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.infojobs.workshop4.R;
import net.infojobs.workshop4.domain.SuperHero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SuperHeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final SuperHeroClickListener superHeroClickListener;
    private List<SuperHero> superHeroes = Collections.emptyList();

    public SuperHeroesAdapter(SuperHeroClickListener superHeroClickListener) {
        this.superHeroClickListener = superHeroClickListener;
    }

    public void setSuperHeroes(Collection<SuperHero> superHeroes) {
        this.superHeroes = new ArrayList<>(superHeroes);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_super_hero, parent, false);
        return new SuperHeroViewHolder(view, superHeroClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SuperHeroViewHolder superHeroViewHolder = (SuperHeroViewHolder) holder;
        SuperHero superHero = superHeroes.get(position);
        superHeroViewHolder.render(superHero);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    private static class SuperHeroViewHolder extends RecyclerView.ViewHolder {

        private final SuperHeroClickListener superHeroClickListener;
        private final ImageView photoImageView;
        private final TextView nameTextView;

        public SuperHeroViewHolder(View itemView, SuperHeroClickListener superHeroClickListener) {
            super(itemView);
            this.superHeroClickListener = superHeroClickListener;
            this.photoImageView = (ImageView) itemView.findViewById(R.id.iv_super_hero_photo);
            this.nameTextView = (TextView) itemView.findViewById(R.id.tv_super_hero_name);
        }

        public void render(SuperHero superHero) {
            hookListeners(superHero);
            renderSuperHeroPhoto(superHero.getPhoto());
            renderSuperHeroName(superHero.getName());
        }

        private void hookListeners(final SuperHero superHero) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    superHeroClickListener.onSuperHeroClick(superHero);
                }
            });
        }

        private void renderSuperHeroPhoto(String photo) {
            Picasso.with(getContext()).load(photo).fit().centerCrop().into(photoImageView);
        }

        private void renderSuperHeroName(String name) {
            nameTextView.setText(name);
        }


        private Context getContext() {
            return itemView.getContext();
        }
    }

    public interface SuperHeroClickListener {
        void onSuperHeroClick(SuperHero superHero);
    }
}
