namespace QuanLyLinhKien
{
    partial class formLinhKien
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
            this.layoutControl1 = new DevExpress.XtraLayout.LayoutControl();
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colMaHang = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colTenHang = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colLoaiHang = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colDonGia = new DevExpress.XtraGrid.Columns.GridColumn();
            this.groupbox1 = new System.Windows.Forms.GroupBox();
            this.button1 = new System.Windows.Forms.Button();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.txbDonGia = new DevExpress.XtraEditors.TextEdit();
            this.cbxLoaiHang = new System.Windows.Forms.ComboBox();
            this.txbTenHang = new DevExpress.XtraEditors.TextEdit();
            this.txbMaHang = new DevExpress.XtraEditors.TextEdit();
            this.layoutControlGroup1 = new DevExpress.XtraLayout.LayoutControlGroup();
            this.layoutControlItem1 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem2 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem3 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem4 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem5 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem6 = new DevExpress.XtraLayout.LayoutControlItem();
            this.layoutControlItem7 = new DevExpress.XtraLayout.LayoutControlItem();
            this.colSoLuongTon = new DevExpress.XtraGrid.Columns.GridColumn();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControl1)).BeginInit();
            this.layoutControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.groupbox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.txbDonGia.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.txbTenHang.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.txbMaHang.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlGroup1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem5)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem6)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem7)).BeginInit();
            this.SuspendLayout();
            // 
            // layoutControl1
            // 
            this.layoutControl1.Controls.Add(this.gridControl1);
            this.layoutControl1.Controls.Add(this.groupbox1);
            this.layoutControl1.Controls.Add(this.textBox1);
            this.layoutControl1.Controls.Add(this.txbDonGia);
            this.layoutControl1.Controls.Add(this.cbxLoaiHang);
            this.layoutControl1.Controls.Add(this.txbTenHang);
            this.layoutControl1.Controls.Add(this.txbMaHang);
            this.layoutControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.layoutControl1.Location = new System.Drawing.Point(0, 0);
            this.layoutControl1.Name = "layoutControl1";
            this.layoutControl1.OptionsCustomizationForm.DesignTimeCustomizationFormPositionAndSize = new System.Drawing.Rectangle(539, 174, 450, 400);
            this.layoutControl1.Root = this.layoutControlGroup1;
            this.layoutControl1.Size = new System.Drawing.Size(827, 422);
            this.layoutControl1.TabIndex = 0;
            this.layoutControl1.Text = "layoutControl1";
            // 
            // gridControl1
            // 
            this.gridControl1.Location = new System.Drawing.Point(12, 158);
            this.gridControl1.MainView = this.gridView1;
            this.gridControl1.Name = "gridControl1";
            this.gridControl1.Size = new System.Drawing.Size(803, 252);
            this.gridControl1.TabIndex = 10;
            this.gridControl1.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colMaHang,
            this.colTenHang,
            this.colLoaiHang,
            this.colDonGia});
            this.gridView1.GridControl = this.gridControl1;
            this.gridView1.Name = "gridView1";
            // 
            // colMaHang
            // 
            this.colMaHang.Caption = "Mã Linh Kiện";
            this.colMaHang.Name = "colMaHang";
            // 
            // colTenHang
            // 
            this.colTenHang.Caption = "Tên Linh Kiện";
            this.colTenHang.Name = "colTenHang";
            // 
            // colLoaiHang
            // 
            this.colLoaiHang.Caption = "Loại Linh Kiện";
            this.colLoaiHang.Name = "colLoaiHang";
            // 
            // colDonGia
            // 
            this.colDonGia.Caption = "Đơn Giá";
            this.colDonGia.Name = "colDonGia";
            // 
            // groupbox1
            // 
            this.groupbox1.Controls.Add(this.button1);
            this.groupbox1.Location = new System.Drawing.Point(12, 85);
            this.groupbox1.Name = "groupbox1";
            this.groupbox1.Size = new System.Drawing.Size(803, 69);
            this.groupbox1.TabIndex = 9;
            this.groupbox1.TabStop = false;
            this.groupbox1.Text = "Chức Năng";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(28, 20);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(81, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(82, 61);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(733, 20);
            this.textBox1.TabIndex = 8;
            // 
            // txbDonGia
            // 
            this.txbDonGia.Location = new System.Drawing.Point(454, 36);
            this.txbDonGia.Name = "txbDonGia";
            this.txbDonGia.Size = new System.Drawing.Size(361, 20);
            this.txbDonGia.StyleController = this.layoutControl1;
            this.txbDonGia.TabIndex = 7;
            // 
            // cbxLoaiHang
            // 
            this.cbxLoaiHang.FormattingEnabled = true;
            this.cbxLoaiHang.Location = new System.Drawing.Point(82, 36);
            this.cbxLoaiHang.Name = "cbxLoaiHang";
            this.cbxLoaiHang.Size = new System.Drawing.Size(298, 21);
            this.cbxLoaiHang.TabIndex = 6;
            // 
            // txbTenHang
            // 
            this.txbTenHang.Location = new System.Drawing.Point(454, 12);
            this.txbTenHang.Name = "txbTenHang";
            this.txbTenHang.Size = new System.Drawing.Size(361, 20);
            this.txbTenHang.StyleController = this.layoutControl1;
            this.txbTenHang.TabIndex = 5;
            // 
            // txbMaHang
            // 
            this.txbMaHang.Location = new System.Drawing.Point(82, 12);
            this.txbMaHang.Name = "txbMaHang";
            this.txbMaHang.Size = new System.Drawing.Size(298, 20);
            this.txbMaHang.StyleController = this.layoutControl1;
            this.txbMaHang.TabIndex = 4;
            // 
            // layoutControlGroup1
            // 
            this.layoutControlGroup1.EnableIndentsWithoutBorders = DevExpress.Utils.DefaultBoolean.True;
            this.layoutControlGroup1.GroupBordersVisible = false;
            this.layoutControlGroup1.Items.AddRange(new DevExpress.XtraLayout.BaseLayoutItem[] {
            this.layoutControlItem1,
            this.layoutControlItem2,
            this.layoutControlItem3,
            this.layoutControlItem4,
            this.layoutControlItem5,
            this.layoutControlItem6,
            this.layoutControlItem7});
            this.layoutControlGroup1.Location = new System.Drawing.Point(0, 0);
            this.layoutControlGroup1.Name = "Root";
            this.layoutControlGroup1.Size = new System.Drawing.Size(827, 422);
            this.layoutControlGroup1.TextVisible = false;
            // 
            // layoutControlItem1
            // 
            this.layoutControlItem1.Control = this.txbMaHang;
            this.layoutControlItem1.Location = new System.Drawing.Point(0, 0);
            this.layoutControlItem1.Name = "layoutControlItem1";
            this.layoutControlItem1.Size = new System.Drawing.Size(372, 24);
            this.layoutControlItem1.Text = "Mã Linh Kiện:";
            this.layoutControlItem1.TextSize = new System.Drawing.Size(67, 13);
            // 
            // layoutControlItem2
            // 
            this.layoutControlItem2.Control = this.txbTenHang;
            this.layoutControlItem2.Location = new System.Drawing.Point(372, 0);
            this.layoutControlItem2.Name = "layoutControlItem2";
            this.layoutControlItem2.Size = new System.Drawing.Size(435, 24);
            this.layoutControlItem2.Text = "Tên Linh Kiện:";
            this.layoutControlItem2.TextSize = new System.Drawing.Size(67, 13);
            // 
            // layoutControlItem3
            // 
            this.layoutControlItem3.Control = this.cbxLoaiHang;
            this.layoutControlItem3.Location = new System.Drawing.Point(0, 24);
            this.layoutControlItem3.Name = "layoutControlItem3";
            this.layoutControlItem3.Size = new System.Drawing.Size(372, 25);
            this.layoutControlItem3.Text = "Loại Linh Kiện";
            this.layoutControlItem3.TextSize = new System.Drawing.Size(67, 13);
            // 
            // layoutControlItem4
            // 
            this.layoutControlItem4.Control = this.txbDonGia;
            this.layoutControlItem4.Location = new System.Drawing.Point(372, 24);
            this.layoutControlItem4.Name = "layoutControlItem4";
            this.layoutControlItem4.Size = new System.Drawing.Size(435, 25);
            this.layoutControlItem4.Text = "Đơn Giá:";
            this.layoutControlItem4.TextSize = new System.Drawing.Size(67, 13);
            // 
            // layoutControlItem5
            // 
            this.layoutControlItem5.Control = this.textBox1;
            this.layoutControlItem5.CustomizationFormText = "Số Lượng Tồn:";
            this.layoutControlItem5.Location = new System.Drawing.Point(0, 49);
            this.layoutControlItem5.Name = "layoutControlItem5";
            this.layoutControlItem5.Size = new System.Drawing.Size(807, 24);
            this.layoutControlItem5.Text = "Số Lượng Tồn";
            this.layoutControlItem5.TextSize = new System.Drawing.Size(67, 13);
            // 
            // layoutControlItem6
            // 
            this.layoutControlItem6.Control = this.groupbox1;
            this.layoutControlItem6.Location = new System.Drawing.Point(0, 73);
            this.layoutControlItem6.Name = "layoutControlItem6";
            this.layoutControlItem6.Size = new System.Drawing.Size(807, 73);
            this.layoutControlItem6.TextLocation = DevExpress.Utils.Locations.Bottom;
            this.layoutControlItem6.TextSize = new System.Drawing.Size(0, 0);
            this.layoutControlItem6.TextVisible = false;
            // 
            // layoutControlItem7
            // 
            this.layoutControlItem7.Control = this.gridControl1;
            this.layoutControlItem7.Location = new System.Drawing.Point(0, 146);
            this.layoutControlItem7.Name = "layoutControlItem7";
            this.layoutControlItem7.Size = new System.Drawing.Size(807, 256);
            this.layoutControlItem7.TextSize = new System.Drawing.Size(0, 0);
            this.layoutControlItem7.TextVisible = false;
            // 
            // colSoLuongTon
            // 
            this.colSoLuongTon.Caption = "Số Lượng Tồn";
            this.colSoLuongTon.Name = "colSoLuongTon";
            // 
            // formLinhKien
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(827, 422);
            this.Controls.Add(this.layoutControl1);
            this.Name = "formLinhKien";
            this.Text = "formLinhKien";
            this.Load += new System.EventHandler(this.formLinhKien_Load);
            ((System.ComponentModel.ISupportInitialize)(this.layoutControl1)).EndInit();
            this.layoutControl1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.groupbox1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.txbDonGia.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.txbTenHang.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.txbMaHang.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlGroup1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem5)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem6)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.layoutControlItem7)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private DevExpress.XtraLayout.LayoutControl layoutControl1;
        private DevExpress.XtraEditors.TextEdit txbMaHang;
        private DevExpress.XtraLayout.LayoutControlGroup layoutControlGroup1;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem1;
        private System.Windows.Forms.ComboBox cbxLoaiHang;
        private DevExpress.XtraEditors.TextEdit txbTenHang;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem2;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem3;
        private System.Windows.Forms.GroupBox groupbox1;
        private System.Windows.Forms.TextBox textBox1;
        private DevExpress.XtraEditors.TextEdit txbDonGia;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem4;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem5;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem6;
        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraLayout.LayoutControlItem layoutControlItem7;
        private DevExpress.XtraGrid.Columns.GridColumn colMaHang;
        private DevExpress.XtraGrid.Columns.GridColumn colTenHang;
        private DevExpress.XtraGrid.Columns.GridColumn colLoaiHang;
        private DevExpress.XtraGrid.Columns.GridColumn colDonGia;
        private DevExpress.XtraGrid.Columns.GridColumn colSoLuongTon;
        private System.Windows.Forms.Button button1;
    }
}