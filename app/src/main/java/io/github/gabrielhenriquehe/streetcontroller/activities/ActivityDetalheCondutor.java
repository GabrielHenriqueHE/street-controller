package io.github.gabrielhenriquehe.streetcontroller.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.adapters.AdapterViewPagerDetalheCondutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;

public class ActivityDetalheCondutor extends AppCompatActivity {

    private TextView txtNomeCondutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe_condutor);

        TabLayout tabLayout = findViewById(R.id.tab_layout_detalhes_condutor);
        ViewPager2 viewPager = findViewById(R.id.vpg_detalhe_condutor);

        Condutor condutor = (Condutor) getIntent().getSerializableExtra("condutor_data");

        AdapterViewPagerDetalheCondutor adapter = new AdapterViewPagerDetalheCondutor(this, condutor);
        viewPager.setAdapter(adapter);

        txtNomeCondutor = findViewById(R.id.txt_nome_condutor);

        if (condutor != null) {
            String nomeCondutor = condutor.getPrimeiroNome() + " " + condutor.getUltimoNome();
            txtNomeCondutor.setText(nomeCondutor);
        }


        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("INFORMAÇÕES");
                        break;
                    case 1:
                        tab.setText("VEÍCULOS");
                        break;
                    default:
                        tab.setText("Erro interno");
                }
            }
        }).attach();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}