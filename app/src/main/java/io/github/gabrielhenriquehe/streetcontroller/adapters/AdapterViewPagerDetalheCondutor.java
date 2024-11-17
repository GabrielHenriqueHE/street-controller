package io.github.gabrielhenriquehe.streetcontroller.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.fragments.FragmentDetalheCondutor;
import io.github.gabrielhenriquehe.streetcontroller.fragments.FragmentVeiculosCondutor;

public class AdapterViewPagerDetalheCondutor extends FragmentStateAdapter {

    private final Condutor condutor;

    public AdapterViewPagerDetalheCondutor(@NonNull FragmentActivity fragmentActivity, Condutor condutor) {
        super(fragmentActivity);
        this.condutor = condutor;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return FragmentDetalheCondutor.newInstance(condutor);
            default:
                return FragmentVeiculosCondutor.newInstance(condutor);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
