using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class ChiTietHoaDonDTO
    {
        private string _MaChiTietHoaDon;
        private string _MaHoaDon;
        private string _MaHang;
        private int _SoLuongBan;

        public string MaChiTietHoaDon
        {
            get
            {
                return _MaChiTietHoaDon;
            }

            set
            {
                _MaChiTietHoaDon = value;
            }
        }

        public string MaHoaDon
        {
            get
            {
                return _MaHoaDon;
            }

            set
            {
                _MaHoaDon = value;
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

        public int SoLuongBan
        {
            get
            {
                return _SoLuongBan;
            }

            set
            {
                _SoLuongBan = value;
            }
        }

        private void New()
        {
            _MaChiTietHoaDon = null;
            _MaHoaDon = null;
            _MaHang = null;
            _SoLuongBan = 0;

        }
}
    }

