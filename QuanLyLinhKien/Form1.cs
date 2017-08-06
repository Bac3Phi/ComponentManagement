using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace QuanLyLinhKien
{
    public partial class Form1 : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void barButtonItem1_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            formControl1 f1 = new formControl1();
            f1.MdiParent = this;
            f1.Show();
        }

        private void barButtonItem2_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            formControl2 f1 = new formControl2();
            f1.MdiParent = this;
            f1.Show();
        }

        private void barButtonItem3_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            formControl1 f1 = new formControl1();
            f1.MdiParent = this;
            f1.Show();
        }

        private void barButtonItem3_ItemClick_1(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            PhieuDatHang f1 = new PhieuDatHang();
            f1.MdiParent = this;
            f1.Show();
        }

        private void barButtonItem4_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            formLinhKien f1 = new formLinhKien();
            f1.MdiParent = this;
            f1.Show();
        }

        private void barButtonItem6_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void ribbonControl1_Click(object sender, EventArgs e)
        {

        }
    }
}
