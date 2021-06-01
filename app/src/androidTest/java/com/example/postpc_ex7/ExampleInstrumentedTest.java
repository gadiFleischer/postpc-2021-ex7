package com.example.postpc_ex7;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    OrderModel testOrder = new OrderModel("gadi",3,true,false,"test");
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.postpc_ex7", appContext.getPackageName());
    }

    @Test
    public void createOrderAndGetItFromDb()
    {
        String testId = testOrder.id;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(appContext);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).set(testOrder);
        db.collection("orders").document(testId).get().addOnSuccessListener(documentSnapshot -> {
            OrderModel order = documentSnapshot.toObject(OrderModel.class);
            assertEquals("gadi", documentSnapshot.getString("name"));
            assertEquals("test", documentSnapshot.getString("comment"));
            assertEquals(3, documentSnapshot.get("pickles"));
            assertTrue(documentSnapshot.getBoolean("hummus"));
            assertFalse(documentSnapshot.getBoolean("tahini"));
        }).addOnFailureListener(documentSnapshot -> {
            assertTrue(false);
        });
    }
    @Test
    public void checkEditDocument()
    {
        String testId = testOrder.id;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(appContext);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).set(testOrder);
        db.collection("orders").document(testId).update("comment", "I dont like making tests");
        db.collection("orders").document(testId).update("pickles", 9);
        db.collection("orders").document(testId).get().addOnSuccessListener(documentSnapshot -> {
            OrderModel order = documentSnapshot.toObject(OrderModel.class);
            assertEquals("gadi", documentSnapshot.getString("name"));
            assertEquals("I dont like making tests", documentSnapshot.getString("comment"));
            assertEquals(9, documentSnapshot.get("pickles"));
        }).addOnFailureListener(documentSnapshot -> {
            assertTrue(false);
        });
    }
}