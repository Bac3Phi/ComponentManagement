namespace QuanLyLinhKien
{
    partial class XtraForm1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tabbedView = new DevExpress.XtraBars.Docking2010.Views.Tabbed.TabbedView(this.components);
            this.ribbonControl = new DevExpress.XtraBars.Ribbon.RibbonControl();
            this.skinRibbonGalleryBarItem = new DevExpress.XtraBars.SkinRibbonGalleryBarItem();
            this.barSubItemNavigation = new DevExpress.XtraBars.BarSubItem();
            this.employeesBarButtonItem = new DevExpress.XtraBars.BarButtonItem();
            this.customersBarButtonItem = new DevExpress.XtraBars.BarButtonItem();
            this.ribbonPage = new DevExpress.XtraBars.Ribbon.RibbonPage();
            this.ribbonPageGroupNavigation = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonPageGroup = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonStatusBar = new DevExpress.XtraBars.Ribbon.RibbonStatusBar();
            this.officeNavigationBar = new DevExpress.XtraBars.Navigation.OfficeNavigationBar();
            this.navBarControl = new DevExpress.XtraNavBar.NavBarControl();
            this.navBarGroup1 = new DevExpress.XtraNavBar.NavBarGroup();
            this.navBarGroupControlContainer1 = new DevExpress.XtraNavBar.NavBarGroupControlContainer();
            this.employeesNavBarGroup = new DevExpress.XtraNavBar.NavBarGroup();
            this.navBarItem1 = new DevExpress.XtraNavBar.NavBarItem();
            this.navBarItem2 = new DevExpress.XtraNavBar.NavBarItem();
            this.customersNavBarGroup = new DevExpress.XtraNavBar.NavBarGroup();
            this.customersNavigationPage = new DevExpress.XtraBars.Navigation.NavigationPage();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.navigationFrame = new DevExpress.XtraBars.Navigation.NavigationFrame();
            this.navigationPage2 = new DevExpress.XtraBars.Navigation.NavigationPage();
            this.navigationPage3 = new DevExpress.XtraBars.Navigation.NavigationPage();
            this.navigationPage4 = new DevExpress.XtraBars.Navigation.NavigationPage();
            this.navigationPage5 = new DevExpress.XtraBars.Navigation.NavigationPage();
            ((System.ComponentModel.ISupportInitialize)(this.tabbedView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ribbonControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.officeNavigationBar)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.navBarControl)).BeginInit();
            this.navBarControl.SuspendLayout();
            this.customersNavigationPage.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.navigationFrame)).BeginInit();
            this.navigationFrame.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabbedView
            // 
            this.tabbedView.RootContainer.Element = null;
            // 
            // ribbonControl
            // 
            this.ribbonControl.ExpandCollapseItem.Id = 0;
            this.ribbonControl.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.ribbonControl.ExpandCollapseItem,
            this.skinRibbonGalleryBarItem,
            this.barSubItemNavigation,
            this.employeesBarButtonItem,
            this.customersBarButtonItem});
            this.ribbonControl.Location = new System.Drawing.Point(0, 0);
            this.ribbonControl.MaxItemId = 46;
            this.ribbonControl.MdiMergeStyle = DevExpress.XtraBars.Ribbon.RibbonMdiMergeStyle.Always;
            this.ribbonControl.Name = "ribbonControl";
            this.ribbonControl.Pages.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPage[] {
            this.ribbonPage});
            this.ribbonControl.RibbonStyle = DevExpress.XtraBars.Ribbon.RibbonControlStyle.Office2013;
            this.ribbonControl.ShowApplicationButton = DevExpress.Utils.DefaultBoolean.False;
            this.ribbonControl.Size = new System.Drawing.Size(837, 147);
            this.ribbonControl.StatusBar = this.ribbonStatusBar;
            this.ribbonControl.ToolbarLocation = DevExpress.XtraBars.Ribbon.RibbonQuickAccessToolbarLocation.Hidden;
            this.ribbonControl.Click += new System.EventHandler(this.ribbonControl_Click);
            // 
            // skinRibbonGalleryBarItem
            // 
            this.skinRibbonGalleryBarItem.Id = 14;
            this.skinRibbonGalleryBarItem.Name = "skinRibbonGalleryBarItem";
            // 
            // barSubItemNavigation
            // 
            this.barSubItemNavigation.Caption = "Navigation";
            this.barSubItemNavigation.Id = 15;
            this.barSubItemNavigation.ImageOptions.ImageUri.Uri = "NavigationBar";
            this.barSubItemNavigation.LinksPersistInfo.AddRange(new DevExpress.XtraBars.LinkPersistInfo[] {
            new DevExpress.XtraBars.LinkPersistInfo(this.employeesBarButtonItem),
            new DevExpress.XtraBars.LinkPersistInfo(this.customersBarButtonItem)});
            this.barSubItemNavigation.Name = "barSubItemNavigation";
            // 
            // employeesBarButtonItem
            // 
            this.employeesBarButtonItem.Caption = "Employees";
            this.employeesBarButtonItem.Id = 44;
            this.employeesBarButtonItem.Name = "employeesBarButtonItem";
            this.employeesBarButtonItem.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barButtonNavigation_ItemClick);
            // 
            // customersBarButtonItem
            // 
            this.customersBarButtonItem.Caption = "Customers";
            this.customersBarButtonItem.Id = 45;
            this.customersBarButtonItem.Name = "customersBarButtonItem";
            this.customersBarButtonItem.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barButtonNavigation_ItemClick);
            // 
            // ribbonPage
            // 
            this.ribbonPage.Groups.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPageGroup[] {
            this.ribbonPageGroupNavigation,
            this.ribbonPageGroup});
            this.ribbonPage.Name = "ribbonPage";
            this.ribbonPage.Text = "View";
            // 
            // ribbonPageGroupNavigation
            // 
            this.ribbonPageGroupNavigation.ItemLinks.Add(this.barSubItemNavigation);
            this.ribbonPageGroupNavigation.Name = "ribbonPageGroupNavigation";
            this.ribbonPageGroupNavigation.Text = "Module";
            // 
            // ribbonPageGroup
            // 
            this.ribbonPageGroup.AllowTextClipping = false;
            this.ribbonPageGroup.ItemLinks.Add(this.skinRibbonGalleryBarItem);
            this.ribbonPageGroup.Name = "ribbonPageGroup";
            this.ribbonPageGroup.ShowCaptionButton = false;
            this.ribbonPageGroup.Text = "Appearance";
            // 
            // ribbonStatusBar
            // 
            this.ribbonStatusBar.Location = new System.Drawing.Point(0, 577);
            this.ribbonStatusBar.Name = "ribbonStatusBar";
            this.ribbonStatusBar.Ribbon = this.ribbonControl;
            this.ribbonStatusBar.Size = new System.Drawing.Size(837, 23);
            // 
            // officeNavigationBar
            // 
            this.officeNavigationBar.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.officeNavigationBar.Location = new System.Drawing.Point(0, 532);
            this.officeNavigationBar.Name = "officeNavigationBar";
            this.officeNavigationBar.NavigationClient = this.navBarControl;
            this.officeNavigationBar.Size = new System.Drawing.Size(837, 45);
            this.officeNavigationBar.TabIndex = 1;
            this.officeNavigationBar.Text = "officeNavigationBar";
            this.officeNavigationBar.Click += new System.EventHandler(this.officeNavigationBar_Click);
            // 
            // navBarControl
            // 
            this.navBarControl.ActiveGroup = this.navBarGroup1;
            this.navBarControl.Controls.Add(this.navBarGroupControlContainer1);
            this.navBarControl.Dock = System.Windows.Forms.DockStyle.Left;
            this.navBarControl.Groups.AddRange(new DevExpress.XtraNavBar.NavBarGroup[] {
            this.employeesNavBarGroup,
            this.customersNavBarGroup,
            this.navBarGroup1});
            this.navBarControl.Items.AddRange(new DevExpress.XtraNavBar.NavBarItem[] {
            this.navBarItem1,
            this.navBarItem2});
            this.navBarControl.Location = new System.Drawing.Point(0, 147);
            this.navBarControl.Name = "navBarControl";
            this.navBarControl.OptionsNavPane.ExpandedWidth = 161;
            this.navBarControl.PaintStyleKind = DevExpress.XtraNavBar.NavBarViewKind.NavigationPane;
            this.navBarControl.Size = new System.Drawing.Size(161, 385);
            this.navBarControl.TabIndex = 0;
            this.navBarControl.Text = "navBarControl";
            this.navBarControl.ActiveGroupChanged += new DevExpress.XtraNavBar.NavBarGroupEventHandler(this.navBarControl_ActiveGroupChanged);
            this.navBarControl.Click += new System.EventHandler(this.navBarControl_Click);
            // 
            // navBarGroup1
            // 
            this.navBarGroup1.Caption = "navBarGroup1";
            this.navBarGroup1.ControlContainer = this.navBarGroupControlContainer1;
            this.navBarGroup1.Expanded = true;
            this.navBarGroup1.GroupClientHeight = 80;
            this.navBarGroup1.GroupStyle = DevExpress.XtraNavBar.NavBarGroupStyle.ControlContainer;
            this.navBarGroup1.Name = "navBarGroup1";
            // 
            // navBarGroupControlContainer1
            // 
            this.navBarGroupControlContainer1.Appearance.BackColor = System.Drawing.SystemColors.Control;
            this.navBarGroupControlContainer1.Appearance.Options.UseBackColor = true;
            this.navBarGroupControlContainer1.Name = "navBarGroupControlContainer1";
            this.navBarGroupControlContainer1.Size = new System.Drawing.Size(161, 197);
            this.navBarGroupControlContainer1.TabIndex = 0;
            // 
            // employeesNavBarGroup
            // 
            this.employeesNavBarGroup.Caption = "sa";
            this.employeesNavBarGroup.ItemLinks.AddRange(new DevExpress.XtraNavBar.NavBarItemLink[] {
            new DevExpress.XtraNavBar.NavBarItemLink(this.navBarItem1),
            new DevExpress.XtraNavBar.NavBarItemLink(this.navBarItem2)});
            this.employeesNavBarGroup.Name = "employeesNavBarGroup";
            // 
            // navBarItem1
            // 
            this.navBarItem1.Caption = "navBarItem1";
            this.navBarItem1.Name = "navBarItem1";
            // 
            // navBarItem2
            // 
            this.navBarItem2.Caption = "navBarItem2";
            this.navBarItem2.Name = "navBarItem2";
            // 
            // customersNavBarGroup
            // 
            this.customersNavBarGroup.Caption = "Customers";
            this.customersNavBarGroup.Name = "customersNavBarGroup";
            // 
            // customersNavigationPage
            // 
            this.customersNavigationPage.Controls.Add(this.textBox1);
            this.customersNavigationPage.Name = "customersNavigationPage";
            this.customersNavigationPage.Size = new System.Drawing.Size(676, 385);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(69, 75);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(100, 21);
            this.textBox1.TabIndex = 2;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // navigationFrame
            // 
            this.navigationFrame.Appearance.BackColor = System.Drawing.Color.White;
            this.navigationFrame.Appearance.Options.UseBackColor = true;
            this.navigationFrame.Controls.Add(this.customersNavigationPage);
            this.navigationFrame.Controls.Add(this.navigationPage2);
            this.navigationFrame.Controls.Add(this.navigationPage3);
            this.navigationFrame.Controls.Add(this.navigationPage4);
            this.navigationFrame.Controls.Add(this.navigationPage5);
            this.navigationFrame.Dock = System.Windows.Forms.DockStyle.Fill;
            this.navigationFrame.Location = new System.Drawing.Point(161, 147);
            this.navigationFrame.Name = "navigationFrame";
            this.navigationFrame.Pages.AddRange(new DevExpress.XtraBars.Navigation.NavigationPageBase[] {
            this.customersNavigationPage,
            this.navigationPage2,
            this.navigationPage3,
            this.navigationPage4,
            this.navigationPage5});
            this.navigationFrame.RibbonAndBarsMergeStyle = DevExpress.XtraBars.Docking2010.Views.RibbonAndBarsMergeStyle.Always;
            this.navigationFrame.SelectedPage = this.navigationPage2;
            this.navigationFrame.Size = new System.Drawing.Size(676, 385);
            this.navigationFrame.TabIndex = 0;
            this.navigationFrame.Text = "navigationFrame";
            // 
            // navigationPage2
            // 
            this.navigationPage2.Name = "navigationPage2";
            this.navigationPage2.Size = new System.Drawing.Size(676, 385);
            this.navigationPage2.Paint += new System.Windows.Forms.PaintEventHandler(this.navigationPage2_Paint);
            // 
            // navigationPage3
            // 
            this.navigationPage3.Name = "navigationPage3";
            this.navigationPage3.Size = new System.Drawing.Size(676, 385);
            // 
            // navigationPage4
            // 
            this.navigationPage4.Name = "navigationPage4";
            this.navigationPage4.Size = new System.Drawing.Size(676, 385);
            // 
            // navigationPage5
            // 
            this.navigationPage5.Name = "navigationPage5";
            this.navigationPage5.Size = new System.Drawing.Size(676, 385);
            this.navigationPage5.Paint += new System.Windows.Forms.PaintEventHandler(this.navigationPage5_Paint);
            // 
            // XtraForm1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(837, 600);
            this.Controls.Add(this.navigationFrame);
            this.Controls.Add(this.navBarControl);
            this.Controls.Add(this.officeNavigationBar);
            this.Controls.Add(this.ribbonStatusBar);
            this.Controls.Add(this.ribbonControl);
            this.Name = "XtraForm1";
            this.Ribbon = this.ribbonControl;
            this.StatusBar = this.ribbonStatusBar;
            this.Load += new System.EventHandler(this.XtraForm1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tabbedView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ribbonControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.officeNavigationBar)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.navBarControl)).EndInit();
            this.navBarControl.ResumeLayout(false);
            this.customersNavigationPage.ResumeLayout(false);
            this.customersNavigationPage.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.navigationFrame)).EndInit();
            this.navigationFrame.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private DevExpress.XtraBars.Docking2010.Views.Tabbed.TabbedView tabbedView;
        private DevExpress.XtraBars.Ribbon.RibbonControl ribbonControl;
        private DevExpress.XtraBars.Ribbon.RibbonPage ribbonPage;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup;
        private DevExpress.XtraBars.Ribbon.RibbonStatusBar ribbonStatusBar;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroupNavigation;
        private DevExpress.XtraBars.BarSubItem barSubItemNavigation;
        private DevExpress.XtraBars.SkinRibbonGalleryBarItem skinRibbonGalleryBarItem;
        private DevExpress.XtraBars.Navigation.OfficeNavigationBar officeNavigationBar;
        private DevExpress.XtraNavBar.NavBarControl navBarControl;
        private DevExpress.XtraNavBar.NavBarGroup employeesNavBarGroup;
        private DevExpress.XtraNavBar.NavBarGroup customersNavBarGroup;
        private DevExpress.XtraBars.BarButtonItem employeesBarButtonItem;
        private DevExpress.XtraBars.BarButtonItem customersBarButtonItem;
        private DevExpress.XtraBars.Navigation.NavigationPage customersNavigationPage;
        private DevExpress.XtraBars.Navigation.NavigationFrame navigationFrame;
        private DevExpress.XtraBars.Navigation.NavigationPage navigationPage2;
        private DevExpress.XtraBars.Navigation.NavigationPage navigationPage3;
        private DevExpress.XtraBars.Navigation.NavigationPage navigationPage4;
        private DevExpress.XtraBars.Navigation.NavigationPage navigationPage5;
        private System.Windows.Forms.TextBox textBox1;
        private DevExpress.XtraNavBar.NavBarItem navBarItem1;
        private DevExpress.XtraNavBar.NavBarGroup navBarGroup1;
        private DevExpress.XtraNavBar.NavBarItem navBarItem2;
        private DevExpress.XtraNavBar.NavBarGroupControlContainer navBarGroupControlContainer1;
    }
}