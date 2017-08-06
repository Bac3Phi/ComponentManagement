using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class NhaCungCapDTO
    {
        private string _MaNCC;
        private string _Ten;
        private string _Email;
        private string _DiaChi;
        private string _SoDienThoai;

        public string MaNCC
        {
            get
            {
                return _MaNCC;
            }

            set
            {
                _MaNCC = value;
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

        private void New()
        {
            MaNCC = null;
            Ten = null;
            Email = null;
            DiaChi = null;
            SoDienThoai = null;
        }
    }
}
