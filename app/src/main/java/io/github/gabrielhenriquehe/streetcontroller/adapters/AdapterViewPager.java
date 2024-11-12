package io.github.gabrielhenriquehe.streetcontroller.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.gabrielhenriquehe.streetcontroller.fragments.FragmentListaCondutores;

public class AdapterViewPager extends FragmentStateAdapter {

    public AdapterViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentListaCondutores();
            default:
                return new FragmentListaCondutores();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}