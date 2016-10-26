using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MathLibrary;

namespace Calculator
{
    public partial class Form1 : Form
    {
        private double memory = 0;
        private Button previousButton = null;
        private String selectedOperation = ""; // "+", "-", "*", "/", ...
        private double firstOperand = 0; //valor da caixa de texto

        public Form1()
        {
            InitializeComponent();
        }

        private void btnNumbers_Click(object sender, EventArgs e)
        {
            //Button btn = (Button) sender;
            Button btn = sender as Button;
            if(btn != null){
                txtResult.Text += btn.Text;
            }
        }

        private void btnMemories_Click(object sender, EventArgs e)
        {

            Button btn = sender as Button;
            if (btn != null)
            {
                switch (btn.Text)
                {
                    case "MC":
                        this.memory = 0;
                        break;
                    default:
                    case "MR":
                        //OU: this.txtResult.Text = "" + this.memory;
                        this.txtResult.Text = this.memory.ToString();
                        //OU: this.txtResult.Text = StringFormat("{0}", this.memory);
                        break;
                    case "MS":
                        this.memory = Double.Parse(txtResult.Text);
                        break;
                    case "M+":
                        this.memory += Double.Parse(txtResult.Text);
                        break;
                    case "M-":
                        this.memory -= Double.Parse(txtResult.Text);
                        break;
                }
            }

        }

        private void btnPoint_Click(object sender, EventArgs e)
        {
            if (txtResult.Text.IndexOf(".") < 0){
                txtResult.Text += ".";
            }
            
        }

        private void btnOperations_Click(object sender, EventArgs e)
        {
            Button btn = sender as Button;

            if (btn != null)
            {
                //cor fundo no botao seleccionado (calculo)
                btn.BackColor = Color.Aqua;
                if(this.previousButton != null)
                {
                    this.previousButton.BackColor = SystemColors.Control;
                }
                this.previousButton = btn;

                //calculos
                this.firstOperand = Double.Parse(this.txtResult.Text);
                this.txtResult.Text = "0";
                this.selectedOperation = btn.Text;
                
            }
        }

        private void btnEquals_Click(object sender, EventArgs e)
        {
            double secondOperand = Double.Parse(txtResult.Text);
            double res = 0;
            switch (this.selectedOperation)
            {
                case "+":
                default:
                    res = MyMath.add(this.firstOperand, secondOperand);
                    break;
                case "-":
                    res = MyMath.sub(this.firstOperand, secondOperand);
                    break;
                case "*":
                    res = MyMath.mult(this.firstOperand, secondOperand);
                    break;
                case "/":
                    res = MyMath.div(this.firstOperand, secondOperand);
                    break;
            }

            this.txtResult.Text = "" + res;
            if (this.previousButton != null){
                this.previousButton.BackColor = SystemColors.Control;
                this.previousButton = null;
            }
            this.firstOperand = 0;
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void toolStripButtonExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void backgroundColor_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem selectedItem = sender as ToolStripMenuItem;

            foreach (ToolStripMenuItem item in selectedItem.GetCurrentParent().Items)
            {
                item.Checked = false;
            }
            selectedItem.Checked = true;
            Color c = Color.Gray;
            if (selectedItem.Tag != null)
            {
                switch (selectedItem.Tag.ToString().ToLower())
                {
                    case "red":
                        c = Color.Red;
                        break;
                    case "green":
                        c = Color.Green;
                        break;
                    case "blue":
                        c = Color.Blue;
                        break;
                    default:
                        break;
                }

                this.BackColor = c;
            }
        }

    }
}
