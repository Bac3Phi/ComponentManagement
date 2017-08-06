using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraEditors;

namespace QuanLyLinhKien
{
    public partial class XtraForm1 : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        public XtraForm1()
        {
            InitializeComponent();
        }
        void navBarControl_ActiveGroupChanged(object sender, DevExpress.XtraNavBar.NavBarGroupEventArgs e)
        {
            navigationFrame.SelectedPageIndex = navBarControl.Groups.IndexOf(e.Group);
        }
        void barButtonNavigation_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            int barItemIndex = barSubItemNavigation.ItemLinks.IndexOf(e.Link);
            navBarControl.ActiveGroup = navBarControl.Groups[barItemIndex];
        }

        private void employeesLabelControl_Click(object sender, EventArgs e)
        {

        }

        private void navBarControl_Click(object sender, EventArgs e)
        {

        }

        private void officeNavigationBar_Click(object sender, EventArgs e)
        {

        }

        private void XtraForm1_Load(object sender, EventArgs e)
        {

        }

        private void navigationPage5_Paint(object sender, PaintEventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void navigationPage2_Paint(object sender, PaintEventArgs e)
        {

        }

        private void ribbonControl_Click(object sender, EventArgs e)
        {

        }
    }
}