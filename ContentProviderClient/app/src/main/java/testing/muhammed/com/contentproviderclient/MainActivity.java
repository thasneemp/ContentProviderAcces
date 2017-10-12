package testing.muhammed.com.contentproviderclient;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import testing.muhammed.com.contentproviderclient.contract.InventoryContract;

public class MainActivity extends AppCompatActivity {
    private TextView mNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameTextView = (TextView) findViewById(R.id.valueText);


        Uri uri = InventoryContract.INVENTORY_AUTHORITY_URI;
        ContentProviderClient client = getContentResolver().acquireContentProviderClient(uri);

        String[] projection = {InventoryContract.InventoryTable.ID, InventoryContract.InventoryTable.NAME};


        try {
            Cursor query = client.query(InventoryContract.INVENTORY_PRODUCT, projection, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String name = query.getString(query.getColumnIndex(InventoryContract.InventoryTable.NAME));
                    mNameTextView.setText(name);
                    query.close();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
