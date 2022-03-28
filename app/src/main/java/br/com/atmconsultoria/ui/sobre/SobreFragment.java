package br.com.atmconsultoria.ui.sobre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.atmconsultoria.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SobreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SobreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SobreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SobreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SobreFragment newInstance(String param1, String param2) {
        SobreFragment fragment = new SobreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sobre, container, false);
        String descricao = getString(R.string.texto_sobre);


        Element versao = new Element();
        versao.setTitle(getString(R.string.title_release));

        Element youtube = new Element();
        youtube.setTitle(getString(R.string.title_youtube));
        youtube.setIconDrawable(R.mipmap.ic_yt);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url  =getString(R.string.url_youtube);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Element email = new Element();
        email.setTitle(getString(R.string.title_email));
        email.setIconDrawable(R.drawable.ic_email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.put_email)});
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.put_subject));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.put_bodytext));
                intent.setType(getString(R.string.set_type));
                startActivity(Intent.createChooser(intent, getString(R.string.chooser_title)));
            }
        });

        return new AboutPage(getActivity())
                //logo e descrição
                .setImage(R.drawable.logo)
                .setDescription(descricao)

                //redes sociais
                .addGroup(getString(R.string.group_description))
                .addItem(youtube)
                .addFacebook(getString(R.string.id_facebook), getString(R.string.title_facebook))
                .addTwitter(getString(R.string.id_twitter), getString(R.string.title_twitter))
                .addInstagram(getString(R.string.id_insta), getString(R.string.title_insta))
                .addPlayStore(getString(R.string.id_playstore), getString(R.string.title_playstore))
                .addGitHub(getString(R.string.id_github), getString(R.string.title_github))
                .addItem(email)

                //versao do app
                .addItem(versao)


                .create();

    }

}