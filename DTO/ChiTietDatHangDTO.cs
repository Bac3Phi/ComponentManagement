using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class ChiTietDatHangDTO
    {
        private string _MaChiTietDatHang;
        private string _MaDatHang;
        private string _MaHang;
        private int _SoLuongDatHang;

        public string MaChiTietDatHang
        {
            get
            {
                return _MaChiTietDatHang;
            }

            set
            {
                _MaChiTietDatHang = value;
            }
        }

        public string MaDatHang
        {
            get
            {
                return _MaDatHang;
            }

            set
            {
                _MaDatHang = value;
            }
        }

        public string MaHang
        {
            get
            {
                return _MaHang;
            }

            set
            {
                _MaHang = value;
            }
        }

        public int SoLuongDatHang
        {
            get
            {
                return _SoLuongDatHang;
            }

            set
            {
                _SoLuongDatHang = value;
            }
        }

        private void New()
        {
            _MaChiTietDatHang = null;
            _MaDatHang= null;
            _MaHang = null;
            _SoLuongDatHang = 0;

        }
    }
}
