/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khannedy.antrian.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.khannedy.antrian.hazelcast.view.FormAntrian;
import java.util.concurrent.BlockingQueue;
import javax.swing.SwingUtilities;

/**
 *
 * @author khannedy
 */
public class Main {

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            hazelcastInstance.shutdown();
        }));

        BlockingQueue<Long> antrian = hazelcastInstance.getQueue("antrian");
        IAtomicLong nomorAntrian = hazelcastInstance.getAtomicLong("nomor-antrian");

        SwingUtilities.invokeLater(() -> {
            FormAntrian form = new FormAntrian(antrian, nomorAntrian);
            form.setVisible(true);
        });
    }

}
