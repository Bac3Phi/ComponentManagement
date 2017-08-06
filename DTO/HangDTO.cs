using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class HangDTO
    {
        private string _MaHang;
        private string _TenHang;
        private string _LoaiHang;
        private float _DonGia;
        private int _SoLuongTon;

        public void New()
        {
            _MaHang = null;
            _TenHang = null;
            _LoaiHang = null;
            _DonGia = 0;
            _SoLuongTon = 0;
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

        public string TenHang
        {
            get
            {
                return _TenHang;
            }

            set
            {
                _TenHang = value;
            }
        }

        public string LoaiHang
        {
            get
            {
                return _LoaiHang;
            }

            set
            {
                _LoaiHang = value;
            }
        }

        public float DonGia
        {
            get
            {
                return _DonGia;
            }

            set
            {
                _DonGia = value;
            }
        }

        public int SoLuongTon
        {
            get
            {
                return _SoLuongTon;
            }

            set
            {
                _SoLuongTon = value;
            }
        }
    }
}
