using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ListBoxDemo
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            int pos = listBox1.SelectedIndex;
            String fruit = listBox1.SelectedItem.ToString();
            //MessageBox.Show(String.Format("Fruit name: {0} on position {1}", fruit, pos));
        }

        private String askSaveFilename()
        {
            SaveFileDialog saveFileDialog1 = new SaveFileDialog();

            saveFileDialog1.Filter = "txt files (*.txt)|*.txt | All files (*.*)|*.*";
            saveFileDialog1.FilterIndex = 1;
            saveFileDialog1.Title = "Save File...";
            saveFileDialog1.RestoreDirectory = true;

            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                return saveFileDialog1.FileName;
            }

            return null;
        }

        private void saveListToFile(String filename, List<String> list)
        {
            System.IO.File.WriteAllLines(filename, list);
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            String filename = this.askSaveFilename();

            if (filename != null)
            {
                MessageBox.Show(filename);
                List<String> list = new List<string>();

                foreach (String item in this.listBox1.Items)
                {
                    list.Add(item);
                }

                this.saveListToFile(filename, list);
            }
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            String filename = @"C:\Users\MV\Documents\as.txt";
            String[] fruits = System.IO.File.ReadAllLines(filename);
            this.listBox1.Items.Clear();
            this.listBox1.Items.AddRange(fruits);
        }
    }
}
