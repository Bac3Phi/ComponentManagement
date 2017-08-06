using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class KhachHangDTO
    {
        private string _MaKhachHang;
        private string _Ten;
        private string _DiaChi;
        private string _Email;
        private string _SoDienThoai;
        private string _Facebook;

        public string MaKhachHang
        {
            get
            {
                return _MaKhachHang;
            }

            set
            {
                _MaKhachHang = value;
            }
        }

        public string Ten
        {
            get
            {
                return _Ten;
            }

            set
            {
                _Ten = value;
            }
        }

        public string DiaChi
        {
            get
            {
                return _DiaChi;
            }

            set
            {
                _DiaChi = value;
            }
        }

        public string Email
        {
            get
            {
                return _Email;
            }

            set
            {
                _Email = value;
            }
        }

        public string SoDienThoai
        {
            get
            {
                return _SoDienThoai;
            }

            set
            {
                _SoDienThoai = value;
            }
        }

        public string Facebook
        {
            get
            {
                return _Facebook;
            }

            set
            {
                _Facebook = value;
            }
        }

        private void New()
        {
            MaKhachHang = null;
            Ten = null;
            DiaChi = null;
            Email = null;
            SoDienThoai = null;
            Facebook = null; 
        }
    }
}
