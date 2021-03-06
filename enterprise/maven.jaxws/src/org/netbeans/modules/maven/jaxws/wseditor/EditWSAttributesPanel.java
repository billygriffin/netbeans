/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.maven.jaxws.wseditor;

import java.awt.Component;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;
import org.netbeans.modules.websvc.api.jaxws.project.config.JaxWsModel;
import org.netbeans.modules.websvc.api.wseditor.InvalidDataException;
import org.netbeans.modules.websvc.api.wseditor.WSEditor;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * @author  Roderico Cruz
 */
public class EditWSAttributesPanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    private StringBuffer description;
    private TreeMap<String, WSEditor> treeMap;
    
    /** Creates new form EditWSAttributesPanel */
    public EditWSAttributesPanel() {
        initComponents();
        initComponents();
        treeMap = new TreeMap<String, WSEditor>(new AlphabeticalComparator());
        description = new StringBuffer("");
    }
    
    public void addTabs(Set<WSEditor> editors, Node node, JaxWsModel jaxWsModel) 
        throws InvalidDataException 
    {
        jTabbedPane1.removeAll();
        treeMap.clear();
        
        //Display tabs in alphabetical order
        for (WSEditor editor : editors){
            treeMap.put(editor.getTitle(), editor);
        }
        
        Set<String> titles = treeMap.keySet();
        for(String title : titles){
            WSEditor editor = treeMap.get(title);
            Component c = editor.createWSEditorComponent(node);
            if (c != null) {
                jTabbedPane1.addTab(title, c);
                String desc = editor.getDescription();
                if(desc != null && !desc.trim().equals("")){
                    c.getAccessibleContext().setAccessibleDescription(desc);
                    description.append(desc);
                }
            }
        }
        String descText = description.toString();
        String helpText = NbBundle.getMessage(EditWSAttributesPanel.class, "MSG_PRESS_F1");
        if(!descText.equals("")){
            descText = "<html>" + descText + " " + helpText + " </html>";
            descLabel.setText(descText);
        }
    }
    
    static class AlphabeticalComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.toLowerCase().compareTo(str2.toLowerCase());
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        descLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        descLabel.setLabelFor(jTabbedPane1);
        org.openide.awt.Mnemonics.setLocalizedText(descLabel, org.openide.util.NbBundle.getMessage(EditWSAttributesPanel.class, "MSG_NO_EDITORS")); // NOI18N
        descLabel.setToolTipText(org.openide.util.NbBundle.getMessage(EditWSAttributesPanel.class, "HINT_EditWSAttributesTopComponent")); // NOI18N
        descLabel.setPreferredSize(new java.awt.Dimension(261, 16));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}
